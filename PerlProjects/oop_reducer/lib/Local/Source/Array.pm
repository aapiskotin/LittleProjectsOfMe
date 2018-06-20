package Local::Source::Array;

use strict;
use warnings;

use base 'Local::Source';

sub next {
	my ($self) = @_;

	return $self->{array}->[ $self->{cur_line}++ ];
}

1;
