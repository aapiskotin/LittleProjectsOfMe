package Local::Reducer;

use strict;
use warnings;

=encoding utf8

=head1 NAME

Local::Reducer - base abstract reducer

=head1 VERSION

Version 1.00

=cut

our $VERSION = '1.00';

=head1 SYNOPSIS

=cut

sub new {
	my ($class, %params) = @_;
	
	$params{reduced} = 0;
	return bless \%params, $class;
}

sub reduced {
	my $self = shift;

	return $self->{reduced};
}

sub reduce_n {
	my ($self, $n) = @_;

	for ( 0 .. ($n - 1) ) {
		$self->reduce_cur_line($self->{source}->next);
	}

	return $self->{reduced};
}

sub reduce_all {
	my $self = shift;

	while ( my $line = $self->{source}->next ) {
		$self->reduce_cur_line($line);
	}

	return $self->{reduced};
}

1;
