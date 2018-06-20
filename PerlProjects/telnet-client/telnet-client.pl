#!/usr/bin/env perl

use strict;
use warnings;
use feature qw(say);

use AnyEvent;
use AnyEvent::Socket;
use AnyEvent::Handle;

my ($host, $port) = @ARGV;

my $cv = AnyEvent->condvar;

my ($server, $user);

$user = AnyEvent::Handle->new(
	fh => \*STDIN,
	on_error => sub {
		say "Fatal error on stadart input";
		$_[0]->destroy;
	},
	on_eof => sub {
		say "Error on standart input";
	},
	on_read => sub {
		$user->push_read( line => sub {
			$server->push_write($_[1]."\r\n");
		});
	}
);

tcp_connect ($host, $port,
	sub {
		my ($server_fh) = @_ or die "$host:$port conection failed: $!";
		
		say "Connected to $host.";
		$server = AnyEvent::Handle->new(
			fh => $server_fh,
			on_error => sub {
				say "Connection problems";
				$_[0]->destroy;
				exit;
			},
			on_eof => sub {
				say "Connection closed by foreign host.";
				$server->destroy;
				exit;
			},
			on_read => sub {
				$server->push_read( line => "\r\n", sub {
					say $_[1];
				});
			},
		);
	},
	sub {
		say "Trying $host.";
	},
);

$cv->recv;
