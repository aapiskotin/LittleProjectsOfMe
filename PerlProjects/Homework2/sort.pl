#!/usr/bin/env perl

use strict;
use 5.016;
use warnings;
use Getopt::Long;
use Scalar::Util 'looks_like_number';

my @input;
while ( my $line = <STDIN> ) {
	chomp $line;
	push @input, $line;
}

my $k = 1;
my ($numeric, $reverse, $unique, $check, $blank, $human);
GetOptions ('k=i' => \$k,
	'n' => \$numeric,
	'r' => \$reverse,
	'u' => \$unique,
    'c' => \$check,
    'b' => \$blank,
    'h' => \$human);
die "Options -hn incompatible" if $numeric && $human;

my @sorted = (@input);
if ($unique) {
	my %uniq;
	@sorted = grep { !$uniq{$_}++ } @sorted;
}
if ($k == 1 && !$blank) {
    @sorted = sort {$a cmp $b} @sorted;
}
else {
    @sorted = sort {column_sort_str($k, $a) cmp column_sort_str($k, $b)} @sorted;
}
if ($human) {
    @sorted = sort {column_sort_hum($k, $a) <=> column_sort_hum($k, $b)} @sorted;
}
if ($numeric) {
	no warnings; #mask numeric comparison warnings
	@sorted = sort {column_sort_num($k, $a) <=> column_sort_num($k, $b)} @sorted;
}
if ($reverse) {
	@sorted = reverse @sorted;
}

if ($check) {
    if (not_eq_arrays(\@input, \@sorted)) {
        my $i = not_eq_arrays(\@input, \@sorted);
        printf ("sort: -:%i: disorder:%s\n", $i, $input[$i - 1]);
    }
}
else {
    say for @sorted;
}

#---------------------------------------------------------------------

sub column_sort_str {
	my ($k, $string) = @_;

	my $columned = left_trim("$string ");
	for my $i (1 .. $k - 1) {
		my $result = index ($columned, ' ');
		if ($result != -1) {
			$columned = substr ($columned, $result + 1);
            if ($blank) { $columned = left_trim($columned); }
            else { $columned = left_trim($columned) if $i != $k - 1 }
		}
	}
	return $columned;
}

sub column_sort_num {
	my ($k, $string) = @_;

	$string = both_sides_trim($string);
	my @nums = split ' ', $string;
	return $nums[$k - 1];
}

sub not_eq_arrays {
    my ($input_aref, $sorted_aref) = @_;

    for my $i (0 .. $#{$input_aref}) {
        return $i + 1 unless ${$input_aref}[$i] eq ${$sorted_aref}[$i];
    }

    return 0;
}

sub right_trim {
    my ($string) = @_;

    my $tail;
    do {
        $tail = "";
        $tail = chop $string;
        $string .= $tail unless $tail eq " " or $tail eq "\t";
    } while ($tail eq " " or $tail eq "\t");
    return $string;
}

sub left_trim {
    my ($string) = @_;

    $string = scalar reverse $string;
    $string = right_trim($string);
    $string = scalar reverse $string;
    return $string;
}

sub both_sides_trim {
    my ($string) = @_;

    $string = right_trim($string);
    $string = left_trim ($string);
    return $string;
}

sub column_sort_hum {
    my ($k, $string) = @_;

    my @suffix = ("y", "z", "a", "f", "p", "n", "µ", "m", "", "k", "M",
                    "G", "T", "P", "E", "Z", "Y");
    my %suffix;
    for my $i (-8 .. 8) {
        $suffix{ $suffix[$i + 8] } = 10**(3 * $i);
    }

    $string = both_sides_trim($string);
    my @nums = split ' ', $string;
    my $current_num = $nums[$k - 1];
    my $tail = chop $current_num;
    return $current_num . $tail unless looks_like_number($current_num);
    return $current_num . $tail if looks_like_number($tail);
    for my $key (keys %suffix) {
        if ($key eq $tail) {
            $current_num *= $suffix{$key};
            return $current_num;
        }
    }
}
