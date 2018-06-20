package Local::Row;

use strict;
use warnings;

sub new {
	my ($class, %params) = @_;

	return bless {}, $class unless length $params{str};
	my @pairs = split /,/, $params{str};
	my %result_hash;
	for my $pair (@pairs) {
		return undef unless $pair =~ $params{parser};
		$result_hash{$1} = $2;
	}
	return bless \%result_hash, $class;
}

sub get {
	my ($self, $name, $default) = @_;

	if (exists $self->{$name}) {
		return $self->{$name};
	}
	else {
		return $default;
	}
}

1;
