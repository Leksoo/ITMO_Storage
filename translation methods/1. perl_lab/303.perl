$text = "";
%res = ();
while(<>){
	$text = $text . $_;
}

while($text =~ /<(\s*)(a|A)(.*\s+)href(\s*)=(\s*)["'](\s*)(.*?)(\s*)["'](.*)>/) {
	$ref = $7;
	$ref =~ /((([^:\/?#]+):)?\/\/)?(.+?(:.+?)?@)?(?<link>[^:\/?#]+)([^?#]*)(:\d+)?(.*?)/i;
	$link = $+{link};
	if(!($link =~ /^\s*$/ || $link =~ /^..$/)){
		$res{$link} = 1;
	}
	$text =~ s/<(\s*)(a|A)(.*\s+)href(\s*)=(\s*)["'](\s*)(.*?)(\s*)["'](.*)>//;
}

for $a (sort keys %res) {
    print $a . "\n";
}


