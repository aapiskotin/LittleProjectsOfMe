#!/usr/bin/env perl

use 5.016;
use strict;
use warnings;
use Scalar::Util 'looks_like_number';

die "Bad arguments" if @ARGV != 1;
my $n = $ARGV[0];
die "Bad arguments" if
        ( !looks_like_number($n) || $n - sprintf("%d", $n) || $n < 1);

my $res = 1;
for my $i (2..$n) {
    $res *= $i;
}

print ("Factorial for $n is $res\n");
