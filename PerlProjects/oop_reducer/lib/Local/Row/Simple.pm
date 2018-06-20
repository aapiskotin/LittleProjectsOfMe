package Local::Row::Simple;

use strict;
use warnings;

use base 'Local::Row';

sub new {
	my ($class, %params) = @_;

	my $self = $class->SUPER::new( str => $params{str},
		parser => qr/^\s*(\w+)\s*:\s*(\w*)\s*$/,
	);

	return $self;
}

1;
