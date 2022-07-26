package com.segmnf.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.database.FirebaseDatabase;
import com.segmnf.myapplication.databinding.ActivityLearnCactivityBinding;
import com.segmnf.myapplication.databinding.ActivityLearnRubyBinding;

import br.tiagohm.codeview.CodeView;
import br.tiagohm.codeview.Language;
import br.tiagohm.codeview.Theme;

public class LearnRubyActivity extends AppCompatActivity implements CodeView.OnHighlightListener {
    ActivityLearnRubyBinding binding;
    private FirebaseDatabase database;


    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void hideStatus() {
        /* To make the status bar transparent*/

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        binding = ActivityLearnRubyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            hideStatus();

        binding.imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String code= "# This is a comment\n" +
                "\n" +
                "=begin\n" +
                "This is a multi-line comment.\n" +
                "The beginning line must start with \"=begin\"\n" +
                "and the ending line must start with \"=end\".\n" +
                "\n" +
                "You can do this, or start each line in\n" +
                "a multi-line comment with the # character.\n" +
                "=end\n" +
                "\n" +
                "# In Ruby, (almost) everything is an object.\n" +
                "# This includes numbers...\n" +
                "3.class #=> Integer\n" +
                "\n" +
                "# ...and strings...\n" +
                "\"Hello\".class #=> String\n" +
                "\n" +
                "# ...and even methods!\n" +
                "\"Hello\".method(:class).class #=> Method\n" +
                "\n" +
                "# Some basic arithmetic\n" +
                "1 + 1 #=> 2\n" +
                "8 - 1 #=> 7\n" +
                "10 * 2 #=> 20\n" +
                "35 / 5 #=> 7\n" +
                "2 ** 5 #=> 32\n" +
                "5 % 3 #=> 2\n" +
                "\n" +
                "# Bitwise operators\n" +
                "3 & 5 #=> 1\n" +
                "3 | 5 #=> 7\n" +
                "3 ^ 5 #=> 6\n" +
                "\n" +
                "# Arithmetic is just syntactic sugar\n" +
                "# for calling a method on an object\n" +
                "1.+(3) #=> 4\n" +
                "10.* 5 #=> 50\n" +
                "100.methods.include?(:/) #=> true\n" +
                "\n" +
                "# Special values are objects\n" +
                "nil # equivalent to null in other languages\n" +
                "true # truth\n" +
                "false # falsehood\n" +
                "\n" +
                "nil.class #=> NilClass\n" +
                "true.class #=> TrueClass\n" +
                "false.class #=> FalseClass\n" +
                "\n" +
                "# Equality\n" +
                "1 == 1 #=> true\n" +
                "2 == 1 #=> false\n" +
                "\n" +
                "# Inequality\n" +
                "1 != 1 #=> false\n" +
                "2 != 1 #=> true\n" +
                "\n" +
                "# Apart from false itself, nil is the only other 'falsey' value\n" +
                "\n" +
                "!!nil   #=> false\n" +
                "!!false #=> false\n" +
                "!!0     #=> true\n" +
                "!!\"\"    #=> true\n" +
                "\n" +
                "# More comparisons\n" +
                "1 < 10 #=> true\n" +
                "1 > 10 #=> false\n" +
                "2 <= 2 #=> true\n" +
                "2 >= 2 #=> true\n" +
                "\n" +
                "# Combined comparison operator (returns `1` when the first argument is greater, \n" +
                "# `-1` when the second argument is greater, and `0` otherwise)\n" +
                "1 <=> 10 #=> -1 (1 < 10)\n" +
                "10 <=> 1 #=> 1 (10 > 1)\n" +
                "1 <=> 1 #=> 0 (1 == 1)\n" +
                "\n" +
                "# Logical operators\n" +
                "true && false #=> false\n" +
                "true || false #=> true\n" +
                "\n" +
                "# There are alternate versions of the logical operators with much lower\n" +
                "# precedence. These are meant to be used as flow-control constructs to chain\n" +
                "# statements together until one of them returns true or false.\n" +
                "\n" +
                "# `do_something_else` only called if `do_something` succeeds.\n" +
                "do_something() and do_something_else()\n" +
                "# `log_error` only called if `do_something` fails.\n" +
                "do_something() or log_error()\n" +
                "\n" +
                "# String interpolation\n" +
                "\n" +
                "placeholder = 'use string interpolation'\n" +
                "\"I can #{placeholder} when using double quoted strings\"\n" +
                "#=> \"I can use string interpolation when using double quoted strings\"\n" +
                "\n" +
                "# You can combine strings using `+`, but not with other types\n" +
                "'hello ' + 'world'  #=> \"hello world\"\n" +
                "'hello ' + 3 #=> TypeError: can't convert Fixnum into String\n" +
                "'hello ' + 3.to_s #=> \"hello 3\"\n" +
                "\"hello #{3}\" #=> \"hello 3\"\n" +
                "\n" +
                "# ...or combine strings and operators\n" +
                "'hello ' * 3 #=> \"hello hello hello \"\n" +
                "\n" +
                "# ...or append to string\n" +
                "'hello' << ' world' #=> \"hello world\"\n" +
                "\n" +
                "# You can print to the output with a newline at the end\n" +
                "puts \"I'm printing!\"\n" +
                "#=> I'm printing!\n" +
                "#=> nil\n" +
                "\n" +
                "# ...or print to the output without a newline\n" +
                "print \"I'm printing!\"\n" +
                "#=> \"I'm printing!\" => nil\n" +
                "\n" +
                "# Variables\n" +
                "x = 25 #=> 25\n" +
                "x #=> 25\n" +
                "\n" +
                "# Note that assignment returns the value assigned.\n" +
                "# This means you can do multiple assignment.\n" +
                "\n" +
                "x = y = 10 #=> 10\n" +
                "x #=> 10\n" +
                "y #=> 10\n" +
                "\n" +
                "# By convention, use snake_case for variable names.\n" +
                "snake_case = true\n" +
                "\n" +
                "# Use descriptive variable names\n" +
                "path_to_project_root = '/good/name/'\n" +
                "m = '/bad/name/'\n" +
                "\n" +
                "# Symbols are immutable, reusable constants represented internally by an\n" +
                "# integer value. They're often used instead of strings to efficiently convey\n" +
                "# specific, meaningful values.\n" +
                "\n" +
                ":pending.class #=> Symbol\n" +
                "\n" +
                "status = :pending\n" +
                "\n" +
                "status == :pending #=> true\n" +
                "\n" +
                "status == 'pending' #=> false\n" +
                "\n" +
                "status == :approved #=> false\n" +
                "\n" +
                "# Strings can be converted into symbols and vice versa.\n" +
                "status.to_s #=> \"pending\"\n" +
                "\"argon\".to_sym #=> :argon\n" +
                "\n" +
                "# Arrays\n" +
                "\n" +
                "# This is an array.\n" +
                "array = [1, 2, 3, 4, 5] #=> [1, 2, 3, 4, 5]\n" +
                "\n" +
                "# Arrays can contain different types of items.\n" +
                "[1, 'hello', false] #=> [1, \"hello\", false]\n" +
                "\n" +
                "# You might prefer %w instead of quotes\n" +
                "%w[foo bar baz] #=> [\"foo\", \"bar\", \"baz\"]\n" +
                "\n" +
                "# Arrays can be indexed.\n" +
                "# From the front...\n" +
                "array[0] #=> 1\n" +
                "array.first #=> 1\n" +
                "array[12] #=> nil\n" +
                "\n" +
                "# ...or from the back...\n" +
                "array[-1] #=> 5\n" +
                "array.last #=> 5\n" +
                "\n" +
                "# ...or with a start index and length...\n" +
                "array[2, 3] #=> [3, 4, 5]\n" +
                "\n" +
                "# ...or with a range...\n" +
                "array[1..3] #=> [2, 3, 4]\n" +
                "\n" +
                "# You can reverse an Array.\n" +
                "# Return a new array with reversed values\n" +
                "[1,2,3].reverse #=> [3,2,1]\n" +
                "# Reverse an array in place to update variable with reversed values\n" +
                "a = [1,2,3]\n" +
                "a.reverse! #=> a==[3,2,1] because of the bang ('!') call to reverse\n" +
                "\n" +
                "# Like arithmetic, [var] access is just syntactic sugar\n" +
                "# for calling a method '[]' on an object.\n" +
                "array.[] 0 #=> 1\n" +
                "array.[] 12 #=> nil\n" +
                "\n" +
                "# You can add to an array...\n" +
                "array << 6 #=> [1, 2, 3, 4, 5, 6]\n" +
                "# Or like this\n" +
                "array.push(6) #=> [1, 2, 3, 4, 5, 6]\n" +
                "\n" +
                "# ...and check if an item exists in an array\n" +
                "array.include?(1) #=> true\n" +
                "\n" +
                "# Hashes are Ruby's primary dictionary with key/value pairs.\n" +
                "# Hashes are denoted with curly braces.\n" +
                "hash = { 'color' => 'green', 'number' => 5 }\n" +
                "\n" +
                "hash.keys #=> ['color', 'number']\n" +
                "\n" +
                "# Hashes can be quickly looked up by key.\n" +
                "hash['color'] #=> \"green\"\n" +
                "hash['number'] #=> 5\n" +
                "\n" +
                "# Asking a hash for a key that doesn't exist returns nil.\n" +
                "hash['nothing here'] #=> nil\n" +
                "\n" +
                "# When using symbols for keys in a hash, you can use an alternate syntax.\n" +
                "\n" +
                "hash = { :defcon => 3, :action => true }\n" +
                "hash.keys #=> [:defcon, :action]\n" +
                "\n" +
                "hash = { defcon: 3, action: true }\n" +
                "hash.keys #=> [:defcon, :action]\n" +
                "\n" +
                "# Check existence of keys and values in hash\n" +
                "hash.key?(:defcon) #=> true\n" +
                "hash.value?(3) #=> true\n" +
                "\n" +
                "# Tip: Both Arrays and Hashes are Enumerable!\n" +
                "# They share a lot of useful methods such as each, map, count, and more.\n" +
                "\n" +
                "# Control structures\n" +
                "\n" +
                "# Conditionals\n" +
                "if true\n" +
                "  'if statement'\n" +
                "elsif false\n" +
                "  'else if, optional'\n" +
                "else\n" +
                "  'else, also optional'\n" +
                "end\n" +
                "\n" +
                "# If a condition controls invocation of a single statement rather than a block of code\n" +
                "# you can use postfix-if notation\n" +
                "warnings = ['Patronimic is missing', 'Address too short']\n" +
                "puts(\"Some warnings occurred:\\n\" + warnings.join(\"\\n\"))  if !warnings.empty?\n" +
                "\n" +
                "# Rephrase condition if `unless` sounds better than `if`\n" +
                "puts(\"Some warnings occurred:\\n\" + warnings.join(\"\\n\"))  unless warnings.empty?\n" +
                "\n" +
                "# Loops\n" +
                "# In Ruby, traditional `for` loops aren't very common. Instead, these \n" +
                "# basic loops are implemented using enumerable, which hinges on `each`.\n" +
                "(1..5).each do |counter|\n" +
                "  puts \"iteration #{counter}\"\n" +
                "end\n" +
                "\n" +
                "# Which is roughly equivalent to the following, which is unusual to see in Ruby.\n" +
                "for counter in 1..5\n" +
                "  puts \"iteration #{counter}\"\n" +
                "end\n" +
                "\n" +
                "# The `do |variable| ... end` construct above is called a 'block'. Blocks are similar\n" +
                "# to lambdas, anonymous functions or closures in other programming languages. They can\n" +
                "# be passed around as objects, called, or attached as methods.\n" +
                "#\n" +
                "# The 'each' method of a range runs the block once for each element of the range.\n" +
                "# The block is passed a counter as a parameter.\n" +
                "\n" +
                "# You can also surround blocks in curly brackets.\n" +
                "(1..5).each { |counter| puts \"iteration #{counter}\" }\n" +
                "\n" +
                "# The contents of data structures can also be iterated using each.\n" +
                "array.each do |element|\n" +
                "  puts \"#{element} is part of the array\"\n" +
                "end\n" +
                "hash.each do |key, value|\n" +
                "  puts \"#{key} is #{value}\"\n" +
                "end\n" +
                "\n" +
                "# If you still need an index you can use 'each_with_index' and define an index\n" +
                "# variable.\n" +
                "array.each_with_index do |element, index|\n" +
                "  puts \"#{element} is number #{index} in the array\"\n" +
                "end\n" +
                "\n" +
                "counter = 1\n" +
                "while counter <= 5 do\n" +
                "  puts \"iteration #{counter}\"\n" +
                "  counter += 1\n" +
                "end\n" +
                "#=> iteration 1\n" +
                "#=> iteration 2\n" +
                "#=> iteration 3\n" +
                "#=> iteration 4\n" +
                "#=> iteration 5\n" +
                "\n" +
                "# There are a bunch of other helpful looping functions in Ruby.\n" +
                "# For example: 'map', 'reduce', 'inject', the list goes on.\n" +
                "# Map, for instance, takes the array it's looping over, does something\n" +
                "# to it as defined in your block, and returns an entirely new array.\n" +
                "array = [1,2,3,4,5]\n" +
                "doubled = array.map do |element|\n" +
                "  element * 2\n" +
                "end\n" +
                "puts doubled\n" +
                "#=> [2,4,6,8,10]\n" +
                "puts array\n" +
                "#=> [1,2,3,4,5]\n" +
                "\n" +
                "# another useful syntax is .map(&:method)\n" +
                "a = [\"FOO\", \"BAR\", \"BAZ\"]\n" +
                "a.map { |s| s.downcase } #=> [\"foo\", \"bar\", \"baz\"]\n" +
                "a.map(&:downcase) #=> [\"foo\", \"bar\", \"baz\"]\n" +
                "\n" +
                "# Case construct\n" +
                "grade = 'B'\n" +
                "\n" +
                "case grade\n" +
                "when 'A'\n" +
                "  puts 'Way to go kiddo'\n" +
                "when 'B'\n" +
                "  puts 'Better luck next time'\n" +
                "when 'C'\n" +
                "  puts 'You can do better'\n" +
                "when 'D'\n" +
                "  puts 'Scraping through'\n" +
                "when 'F'\n" +
                "  puts 'You failed!'\n" +
                "else\n" +
                "  puts 'Alternative grading system, eh?'\n" +
                "end\n" +
                "#=> \"Better luck next time\"\n" +
                "\n" +
                "# Cases can also use ranges\n" +
                "grade = 82\n" +
                "case grade\n" +
                "when 90..100\n" +
                "  puts 'Hooray!'\n" +
                "when 80...90\n" +
                "  puts 'OK job'\n" +
                "else\n" +
                "  puts 'You failed!'\n" +
                "end\n" +
                "#=> \"OK job\"\n" +
                "\n" +
                "# Exception handling\n" +
                "begin\n" +
                "  # Code here that might raise an exception\n" +
                "  raise NoMemoryError, 'You ran out of memory.'\n" +
                "rescue NoMemoryError => exception_variable\n" +
                "  puts 'NoMemoryError was raised', exception_variable\n" +
                "rescue RuntimeError => other_exception_variable\n" +
                "  puts 'RuntimeError was raised now'\n" +
                "else\n" +
                "  puts 'This runs if no exceptions were thrown at all'\n" +
                "ensure\n" +
                "  puts 'This code always runs no matter what'\n" +
                "end\n" +
                "\n" +
                "# Methods\n" +
                "\n" +
                "def double(x)\n" +
                "  x * 2\n" +
                "end\n" +
                "\n" +
                "# Methods (and blocks) implicitly return the value of the last statement.\n" +
                "double(2) #=> 4\n" +
                "\n" +
                "# Parentheses are optional where the interpretation is unambiguous.\n" +
                "double 3 #=> 6\n" +
                "\n" +
                "double double 3 #=> 12\n" +
                "\n" +
                "def sum(x, y)\n" +
                "  x + y\n" +
                "end\n" +
                "\n" +
                "# Method arguments are separated by a comma.\n" +
                "sum 3, 4 #=> 7\n" +
                "\n" +
                "sum sum(3, 4), 5 #=> 12\n" +
                "\n" +
                "# yield\n" +
                "# All methods have an implicit, optional block parameter.\n" +
                "# It can be called with the 'yield' keyword.\n" +
                "def surround\n" +
                "  puts '{'\n" +
                "  yield\n" +
                "  puts '}'\n" +
                "end\n" +
                "\n" +
                "surround { puts 'hello world' }\n" +
                "\n" +
                "#=> {\n" +
                "#=> hello world\n" +
                "#=> }\n" +
                "\n" +
                "# Blocks can be converted into a 'proc' object, which wraps the block \n" +
                "# and allows it to be passed to another method, bound to a different scope,\n" +
                "# or manipulated otherwise. This is most common in method parameter lists,\n" +
                "# where you frequently see a trailing '&block' parameter that will accept \n" +
                "# the block, if one is given, and convert it to a 'Proc'. The naming here is\n" +
                "# convention; it would work just as well with '&pineapple'.\n" +
                "def guests(&block)\n" +
                "  block.class #=> Proc\n" +
                "  block.call(4)\n" +
                "end\n" +
                "\n" +
                "# The 'call' method on the Proc is similar to calling 'yield' when a block is \n" +
                "# present. The arguments passed to 'call' will be forwarded to the block as arguments.\n" +
                "\n" +
                "guests { |n| \"You have #{n} guests.\" }\n" +
                "# => \"You have 4 guests.\"\n" +
                "\n" +
                "# You can pass a list of arguments, which will be converted into an array.\n" +
                "# That's what splat operator (\"*\") is for.\n" +
                "def guests(*array)\n" +
                "  array.each { |guest| puts guest }\n" +
                "end\n" +
                "\n" +
                "# There is also the shorthand block syntax. It's most useful when you need\n" +
                "# to call a simple method on all array items.\n" +
                "upcased = ['Watch', 'these', 'words', 'get', 'upcased'].map(&:upcase)\n" +
                "puts upcased\n" +
                "#=> [\"WATCH\", \"THESE\", \"WORDS\", \"GET\", \"UPCASED\"]\n" +
                "\n" +
                "sum = [1, 2, 3, 4, 5].reduce(&:+)\n" +
                "puts sum\n" +
                "#=> 15\n" +
                "\n" +
                "# Destructuring\n" +
                "\n" +
                "# Ruby will automatically destructure arrays on assignment to multiple variables.\n" +
                "a, b, c = [1, 2, 3]\n" +
                "a #=> 1\n" +
                "b #=> 2\n" +
                "c #=> 3\n" +
                "\n" +
                "# In some cases, you will want to use the splat operator: `*` to prompt destructuring\n" +
                "# of an array into a list.\n" +
                "ranked_competitors = [\"John\", \"Sally\", \"Dingus\", \"Moe\", \"Marcy\"]\n" +
                "\n" +
                "def best(first, second, third)\n" +
                "  puts \"Winners are #{first}, #{second}, and #{third}.\"\n" +
                "end\n" +
                "\n" +
                "best *ranked_competitors.first(3) #=> Winners are John, Sally, and Dingus.\n" +
                "\n" +
                "# The splat operator can also be used in parameters.\n" +
                "def best(first, second, third, *others)\n" +
                "  puts \"Winners are #{first}, #{second}, and #{third}.\"\n" +
                "  puts \"There were #{others.count} other participants.\"\n" +
                "end\n" +
                "\n" +
                "best *ranked_competitors \n" +
                "#=> Winners are John, Sally, and Dingus.\n" +
                "#=> There were 2 other participants.\n" +
                "\n" +
                "# By convention, all methods that return booleans end with a question mark.\n" +
                "5.even? #=> false\n" +
                "5.odd? #=> true\n" +
                "\n" +
                "# By convention, if a method name ends with an exclamation mark, it does something destructive\n" +
                "# like mutate the receiver. Many methods have a ! version to make a change, and\n" +
                "# a non-! version to just return a new changed version.\n" +
                "company_name = \"Dunder Mifflin\"\n" +
                "company_name.upcase #=> \"DUNDER MIFFLIN\"\n" +
                "company_name #=> \"Dunder Mifflin\"\n" +
                "# We're mutating company_name this time.\n" +
                "company_name.upcase! #=> \"DUNDER MIFFLIN\"\n" +
                "company_name #=> \"DUNDER MIFFLIN\"\n" +
                "\n" +
                "# Classes\n" +
                "\n" +
                "# You can define a class with the 'class' keyword.\n" +
                "class Human\n" +
                "\n" +
                "  # A class variable. It is shared by all instances of this class.\n" +
                "  @@species = 'H. sapiens'\n" +
                "\n" +
                "  # Basic initializer\n" +
                "  def initialize(name, age = 0)\n" +
                "    # Assign the argument to the 'name' instance variable for the instance.\n" +
                "    @name = name\n" +
                "    # If no age given, we will fall back to the default in the arguments list.\n" +
                "    @age = age\n" +
                "  end\n" +
                "\n" +
                "  # Basic setter method\n" +
                "  def name=(name)\n" +
                "    @name = name\n" +
                "  end\n" +
                "\n" +
                "  # Basic getter method\n" +
                "  def name\n" +
                "    @name\n" +
                "  end\n" +
                "\n" +
                "  # The above functionality can be encapsulated using the attr_accessor method as follows.\n" +
                "  attr_accessor :name\n" +
                "\n" +
                "  # Getter/setter methods can also be created individually like this.\n" +
                "  attr_reader :name\n" +
                "  attr_writer :name\n" +
                "\n" +
                "  # A class method uses self to distinguish from instance methods.\n" +
                "  # It can only be called on the class, not an instance.\n" +
                "  def self.say(msg)\n" +
                "    puts msg\n" +
                "  end\n" +
                "\n" +
                "  def species\n" +
                "    @@species\n" +
                "  end\n" +
                "end\n" +
                "\n" +
                "# Instantiating of a class\n" +
                "jim = Human.new('Jim Halpert')\n" +
                "dwight = Human.new('Dwight K. Schrute')\n" +
                "\n" +
                "# You can call the methods of the generated object.\n" +
                "jim.species #=> \"H. sapiens\"\n" +
                "jim.name #=> \"Jim Halpert\"\n" +
                "jim.name = \"Jim Halpert II\" #=> \"Jim Halpert II\"\n" +
                "jim.name #=> \"Jim Halpert II\"\n" +
                "dwight.species #=> \"H. sapiens\"\n" +
                "dwight.name #=> \"Dwight K. Schrute\"\n" +
                "\n" +
                "# Calling of a class method\n" +
                "Human.say('Hi') #=> \"Hi\"\n" +
                "\n" +
                "# Variable's scopes are defined by the way we name them.\n" +
                "# Variables that start with $ have global scope.\n" +
                "$var = \"I'm a global var\"\n" +
                "defined? $var #=> \"global-variable\"\n" +
                "\n" +
                "# Variables that start with @ have instance scope.\n" +
                "@var = \"I'm an instance var\"\n" +
                "defined? @var #=> \"instance-variable\"\n" +
                "\n" +
                "# Variables that start with @@ have class scope.\n" +
                "@@var = \"I'm a class var\"\n" +
                "defined? @@var #=> \"class variable\"\n" +
                "\n" +
                "# Variables that start with a capital letter are constants.\n" +
                "Var = \"I'm a constant\"\n" +
                "defined? Var #=> \"constant\"\n" +
                "\n" +
                "# Class is also an object in ruby. So a class can have instance variables.\n" +
                "# A class variable is shared among the class and all of its descendants.\n" +
                "\n" +
                "# Base class\n" +
                "class Human\n" +
                "  @@foo = 0\n" +
                "\n" +
                "  def self.foo\n" +
                "    @@foo\n" +
                "  end\n" +
                "\n" +
                "  def self.foo=(value)\n" +
                "    @@foo = value\n" +
                "  end\n" +
                "end\n" +
                "\n" +
                "# Derived class\n" +
                "class Worker < Human\n" +
                "end\n" +
                "\n" +
                "Human.foo #=> 0\n" +
                "Worker.foo #=> 0\n" +
                "\n" +
                "Human.foo = 2\n" +
                "Worker.foo #=> 2\n" +
                "\n" +
                "# A class instance variable is not shared by the class's descendants.\n" +
                "class Human\n" +
                "  @bar = 0\n" +
                "\n" +
                "  def self.bar\n" +
                "    @bar\n" +
                "  end\n" +
                "\n" +
                "  def self.bar=(value)\n" +
                "    @bar = value\n" +
                "  end\n" +
                "end\n" +
                "\n" +
                "class Doctor < Human\n" +
                "end\n" +
                "\n" +
                "Human.bar #=> 0\n" +
                "Doctor.bar #=> nil\n" +
                "\n" +
                "module ModuleExample\n" +
                "  def foo\n" +
                "    'foo'\n" +
                "  end\n" +
                "end\n" +
                "\n" +
                "# Including modules binds their methods to the class instances.\n" +
                "# Extending modules binds their methods to the class itself.\n" +
                "class Person\n" +
                "  include ModuleExample\n" +
                "end\n" +
                "\n" +
                "class Book\n" +
                "  extend ModuleExample\n" +
                "end\n" +
                "\n" +
                "Person.foo     #=> NoMethodError: undefined method `foo' for Person:Class\n" +
                "Person.new.foo #=> \"foo\"\n" +
                "Book.foo       #=> \"foo\"\n" +
                "Book.new.foo   #=> NoMethodError: undefined method `foo'\n" +
                "\n" +
                "# Callbacks are executed when including and extending a module\n" +
                "module ConcernExample\n" +
                "  def self.included(base)\n" +
                "    base.extend(ClassMethods)\n" +
                "    base.send(:include, InstanceMethods)\n" +
                "  end\n" +
                "\n" +
                "  module ClassMethods\n" +
                "    def bar\n" +
                "      'bar'\n" +
                "    end\n" +
                "  end\n" +
                "\n" +
                "  module InstanceMethods\n" +
                "    def qux\n" +
                "      'qux'\n" +
                "    end\n" +
                "  end\n" +
                "end\n" +
                "\n" +
                "class Something\n" +
                "  include ConcernExample\n" +
                "end\n" +
                "\n" +
                "Something.bar     #=> \"bar\"\n" +
                "Something.qux     #=> NoMethodError: undefined method `qux'\n" +
                "Something.new.bar #=> NoMethodError: undefined method `bar'\n" +
                "Something.new.qux #=> \"qux\"";
        database = FirebaseDatabase.getInstance("https://tynkr-3915c-default-rtdb.asia-southeast1.firebasedatabase.app/");
        binding.codeView.setOnHighlightListener(this)
                .setOnHighlightListener(this)
                .setTheme(Theme.MONOKAI_SUBLIME)
                .setCode(code)
                .setLanguage(Language.RUBY)
                .setWrapLine(false)
                .setFontSize(13)
                .setZoomEnabled(true)
                .setShowLineNumber(true)
                .apply();

    }

    @Override
    public void onStartCodeHighlight() {

    }

    @Override
    public void onFinishCodeHighlight() {

    }

    @Override
    public void onLanguageDetected(Language language, int relevance) {

    }

    @Override
    public void onFontSizeChanged(int sizeInPx) {

    }

    @Override
    public void onLineClicked(int lineNumber, String content) {

    }
}
