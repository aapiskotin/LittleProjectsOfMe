package Local::Reducer::MaxDiff;

use strict;
use warnings;
use Scalar::Util qw(looks_like_number);
use List::Util qw(max);

use base 'Local::Reducer';

sub reduce_cur_line {
	my ($self, $line) = @_;

	my $row = $self->{row_class}->new( str => $line );
	if (defined $row) {
		my $top_val = $row->get($self->{top}, 0);
		my $bot_val = $row->get($self->{bottom}, 0);
		if ( looks_like_number($top_val) and looks_like_number($bot_val) ) {
			$self->{reduced} = max ($self->{reduced}, $top_val - $bot_val);
		}
	}
}

1;
