#!/usr/bin/env perl

use 5.016;
use warnings;
use strict;
use Scalar::Util 'looks_like_number';

foreach ( @ARGV ) {
    die "Bad arguments" unless looks_like_number($_);    
}

my ($a,$b,$c) = @ARGV;
die "Not a quadratic equation" if $a == 0;
$b = 0 unless $b;
$c = 0 unless $c;

my $result1;
my $result2;
my $equation = "$a\*x^2";
if ($b > 0) {
    $equation = "$equation + $b\*x";
}
elsif ($b < 0) {
    $equation = "$equation $b\*x";
}
if ($c > 0) {
    $equation = "$equation + $c";
}
elsif ($c < 0) {
    $equation = "$equation $c";
}
$equation = "$equation = 0";

if (@ARGV == 1) {
    $result1 = 0;
    printf("Equation: %s\nAnswer: %f\n",
            $equation, $result1);
}
elsif (@ARGV == 2) {
    $result1 = 0;
    $result2 = -$b/$a;
    printf("Equation: %s\nAnswer: %f\t%f\n",
            $equation, $result1, $result2);
}
elsif(@ARGV == 3) {
    my $discriminant = $b**2 - 4*$a*$c;
    if ($discriminant >= 0) {
        $result1 = (-$b + sqrt($discriminant))/2/$a;
        $result2 = (-$b - sqrt($discriminant))/2/$a;
        printf("Equation: %s\nAnswer: %f\t%f\n",
                $equation, $result1, $result2);
    }
    else {
        say "No roots in real numbers";
    }
}
else {
    die "Bad arguments";
}
