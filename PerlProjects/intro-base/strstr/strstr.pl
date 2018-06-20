#!/usr/bin/env perl

use 5.016;
use warnings;
use strict;

die "Bad arguments" if ( @ARGV != 2 );

my ( $haystack, $needle ) = @ARGV;
my $result = index ( $haystack, $needle );
if ( $result == -1 ) {
	warn "String not found";
	exit;
}
else {
	say ($result + 1);
	say substr ( $haystack, $result, ( length $haystack ) - $result );
}
