#!/usr/bin/env perl

use strict;
use warnings;

use AnyEvent;
use AnyEvent::Socket;
use AnyEvent::Handle;

my %memcache;

my $cv = AnyEvent->condvar;

tcp_server undef, 9999, sub {
	my ($fh, $host, $port) = @_;

	my $handle; $handle = AnyEvent::Handle->new (
		fh => $fh,
		on_error => sub {
			warn "err in handle";
			$handle->push_write("CLIENT_ERROR $_[2]\r\n");
			$_[0]->destroy;
		},
		on_eof => sub {
			warn "eof in handle";
			$handle->destroy;
		},
		on_read => sub {
			$handle->push_read(
				line => "\015\012",
				sub {
					my ($handle, $param_line) = @_;
					my ($cmd) = $param_line =~ /^(\w*) /;
					if ($cmd =~ /^gets?$/) {
						my @keys = split / /, $param_line;
						shift @keys;
						for my $key (@keys) {
							next if is_expired($key, \%memcache);
							my $row = $memcache{$key};
							next unless $row;
							my $data = $row->{data};
							my $length = length $data;
							$handle->push_write("VALUE $key $row->{flag} $length \r\n$data\r\n");
						}
						$handle->push_write("END\r\n");
					}
					elsif ($cmd =~ /^(?:set)|(?:add)$/) {
						$handle->push_read(
							line => "\015\012",
							sub {
								my ($handle, $data) = @_;
								my $status = set_add_data($param_line, $data, \%memcache);
								$handle->push_write($status);
							}
						);
					}
					else {
						$handle->push_write("ERROR\r\n");
					}
				}
			);
		},
	);
};

my $clean_cache; $clean_cache = AnyEvent->idle( cb => sub {
		my $i = 0;
		for my $key (keys %memcache) {
			is_expired($key, \%memcache);
			$i++;
			last if $i > 50;
		}
	});

$cv->recv;

sub set_add_data {
	my ($param_line, $data, $memcache) = @_;

	my ($cmd, $key, $flag, $exp_time, $length) = $param_line =~ /^(\w+) (\w+) (\d+) (\d+) (\d+)/ or return "CLIENT_ERROR Bad Request\r\n";
	my $exp_date;
	if ($exp_time > 60*60*24*30) {
		$exp_date = $exp_time;
	}
	else {
		$exp_date = time + $exp_time;
	}
	
	return "NOT_STORED\r\n" if $cmd eq 'add' and exists $memcache->{$key};
	$memcache->{$key} = {
		flag     => $flag,
		exp_date => $exp_date,
		data     => substr($data, 0, $length),
	};
	return "STORED\r\n";
}

sub is_expired {
	my ($key) = @_;

	return 1 unless exists $memcache{$key};
	if ($memcache{$key}->{exp_date} < time) {
		delete $memcache{$key};
		return 1;
	}
	else {
		return '';
	}
}
