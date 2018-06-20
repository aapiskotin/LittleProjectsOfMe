package Local::Source::Text;

use strict;
use warnings;

use base 'Local::Source';

sub next {
	my $self = shift;

	my $delimiter = $self->{delimiter} // "\n";
	$self->{lines_arr} //= [ split /$delimiter/, $self->{text} ];

	return $self->{lines_arr}->[ $self->{cur_line}++ ];
}


1;
