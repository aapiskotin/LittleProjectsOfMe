package Local::Row::JSON;

use strict;
use warnings;
use JSON::XS;

use base 'Local::Row';

sub new {
	my ($class, %params) = @_;

	eval {
		my $data = decode_json $params{str};

		die "Not a HASH" unless ref($data) eq 'HASH';
		return bless $data, $class;
	1} or do {
		return undef;
	};
}

1;
