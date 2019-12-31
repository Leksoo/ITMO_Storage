while (<>) {
	s/\b(\w+)0\b/$1/g;
	print;
}
