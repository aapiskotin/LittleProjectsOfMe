#!/usr/bin/env perl

use 5.016;
use warnings;
use strict;
use Scalar::Util 'looks_like_number';

die "Bad arguments" if @ARGV != 1;
my $n = $ARGV[0];
die "Bad arguments" if
        ( !looks_like_number($n) || $n - sprintf("%d", $n) || $n < 1);

my $f0 = 0;
my $f1 = 1;

if ($n == 1) {
    say "Your Fibonacci number is $f1";
}
else {
    my $sum;
    for (2 .. $n) {
        $sum = $f1 + $f0;
        $f0 = $f1;
        $f1 = $sum;
    }
    say "Your Fibonacci number is $sum";
}
