package Local::Reducer::Sum;

use strict;
use warnings;
use Scalar::Util qw(looks_like_number);

use base 'Local::Reducer';

sub reduce_cur_line {
	my ($self, $line) = @_;
	my $row = $self->{row_class}->new( str => $line );
	my $value = $row->get($self->{field}, 0) if defined $row;

	$self->{reduced} += $value if looks_like_number($value);
}

1;
