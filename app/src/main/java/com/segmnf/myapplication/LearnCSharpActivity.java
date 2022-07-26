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
import com.segmnf.myapplication.databinding.ActivityLearnCsharpBinding;

import br.tiagohm.codeview.CodeView;
import br.tiagohm.codeview.Language;
import br.tiagohm.codeview.Theme;

public class LearnCSharpActivity extends AppCompatActivity implements CodeView.OnHighlightListener {
    ActivityLearnCsharpBinding binding;
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
        binding = ActivityLearnCsharpBinding.inflate(getLayoutInflater());
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
        String code= "// Single-line comments start with //\n" +
                "\n" +
                "/*\n" +
                "Multi-line comments look like this\n" +
                "*/\n" +
                "\n" +
                "/// <summary>\n" +
                "/// This is an XML documentation comment which can be used to generate external\n" +
                "/// documentation or provide context help within an IDE\n" +
                "/// </summary>\n" +
                "/// <param name=\"firstParam\">This is some parameter documentation for firstParam</param>\n" +
                "/// <returns>Information on the returned value of a function</returns>\n" +
                "public void MethodOrClassOrOtherWithParsableHelp(string firstParam) { }\n" +
                "\n" +
                "// Specify the namespaces this source code will be using\n" +
                "// The namespaces below are all part of the standard .NET Framework Class Library\n" +
                "using System;\n" +
                "using System.Collections.Generic;\n" +
                "using System.Dynamic;\n" +
                "using System.Linq;\n" +
                "using System.Net;\n" +
                "using System.Threading.Tasks;\n" +
                "using System.IO;\n" +
                "\n" +
                "// But this one is not:\n" +
                "using System.Data.Entity;\n" +
                "// In order to be able to use it, you need to add a dll reference\n" +
                "// This can be done with the NuGet package manager: `Install-Package EntityFramework`\n" +
                "\n" +
                "// Namespaces define scope to organize code into \"packages\" or \"modules\"\n" +
                "// Using this code from another source file: using Learning.CSharp;\n" +
                "\n" +
                "// You can also do this in C# 10, it is called file-scoped namespaces.\n" +
                "// namespace Learning.CSharp;\n" +
                "\n" +
                "namespace Learning.CSharp\n" +
                "{\n" +
                "    // Each .cs file should at least contain a class with the same name as the file.\n" +
                "    // You're allowed to do otherwise, but shouldn't for sanity.\n" +
                "    public class LearnCSharp\n" +
                "    {\n" +
                "        // BASIC SYNTAX - skip to INTERESTING FEATURES if you have used Java or C++ before\n" +
                "        public static void Syntax()\n" +
                "        {\n" +
                "            // Use Console.WriteLine to print lines\n" +
                "            Console.WriteLine(\"Hello World\");\n" +
                "            Console.WriteLine(\n" +
                "                \"Integer: \" + 10 +\n" +
                "                \" Double: \" + 3.14 +\n" +
                "                \" Boolean: \" + true);\n" +
                "\n" +
                "            // To print without a new line, use Console.Write\n" +
                "            Console.Write(\"Hello \");\n" +
                "            Console.Write(\"World\");\n" +
                "\n" +
                "            ///////////////////////////////////////////////////\n" +
                "            // Types & Variables\n" +
                "            //\n" +
                "            // Declare a variable using <type> <name>\n" +
                "            ///////////////////////////////////////////////////\n" +
                "\n" +
                "            // Sbyte - Signed 8-bit integer\n" +
                "            // (-128 <= sbyte <= 127)\n" +
                "            sbyte fooSbyte = 100;\n" +
                "\n" +
                "            // Byte - Unsigned 8-bit integer\n" +
                "            // (0 <= byte <= 255)\n" +
                "            byte fooByte = 100;\n" +
                "\n" +
                "            // Short - 16-bit integer\n" +
                "            // Signed - (-32,768 <= short <= 32,767)\n" +
                "            // Unsigned - (0 <= ushort <= 65,535)\n" +
                "            short fooShort = 10000;\n" +
                "            ushort fooUshort = 10000;\n" +
                "\n" +
                "            // Integer - 32-bit integer\n" +
                "            int fooInt = 1; // (-2,147,483,648 <= int <= 2,147,483,647)\n" +
                "            uint fooUint = 1; // (0 <= uint <= 4,294,967,295)\n" +
                "\n" +
                "            // Long - 64-bit integer\n" +
                "            long fooLong = 100000L; // (-9,223,372,036,854,775,808 <= long <= 9,223,372,036,854,775,807)\n" +
                "            ulong fooUlong = 100000L; // (0 <= ulong <= 18,446,744,073,709,551,615)\n" +
                "            // Numbers default to being int or uint depending on size.\n" +
                "            // L is used to denote that this variable value is of type long or ulong\n" +
                "\n" +
                "            // Double - Double-precision 64-bit IEEE 754 Floating Point\n" +
                "            double fooDouble = 123.4; // Precision: 15-16 digits\n" +
                "\n" +
                "            // Float - Single-precision 32-bit IEEE 754 Floating Point\n" +
                "            float fooFloat = 234.5f; // Precision: 7 digits\n" +
                "            // f is used to denote that this variable value is of type float\n" +
                "\n" +
                "            // Decimal - a 128-bits data type, with more precision than other floating-point types,\n" +
                "            // suited for financial and monetary calculations\n" +
                "            decimal fooDecimal = 150.3m;\n" +
                "\n" +
                "            // Boolean - true & false\n" +
                "            bool fooBoolean = true; // or false\n" +
                "\n" +
                "            // Char - A single 16-bit Unicode character\n" +
                "            char fooChar = 'A';\n" +
                "\n" +
                "            // Strings -- unlike the previous base types which are all value types,\n" +
                "            // a string is a reference type. That is, you can set it to null\n" +
                "            string fooString = \"\\\"escape\\\" quotes and add \\n (new lines) and \\t (tabs)\";\n" +
                "            Console.WriteLine(fooString);\n" +
                "\n" +
                "            // You can access each character of the string with an indexer:\n" +
                "            char charFromString = fooString[1]; // => 'e'\n" +
                "            // Strings are immutable: you can't do fooString[1] = 'X';\n" +
                "\n" +
                "            // Compare strings with current culture, ignoring case\n" +
                "            string.Compare(fooString, \"x\", StringComparison.CurrentCultureIgnoreCase);\n" +
                "\n" +
                "            // Formatting, based on sprintf\n" +
                "            string fooFs = string.Format(\"Check Check, {0} {1}, {0} {1:0.0}\", 1, 2);\n" +
                "\n" +
                "            // Dates & Formatting\n" +
                "            DateTime fooDate = DateTime.Now;\n" +
                "            Console.WriteLine(fooDate.ToString(\"hh:mm, dd MMM yyyy\"));\n" +
                "\n" +
                "            // Verbatim String\n" +
                "            // You can use the @ symbol before a string literal to escape all characters in the string\n" +
                "            string path = \"C:\\\\Users\\\\User\\\\Desktop\";\n" +
                "            string verbatimPath = @\"C:\\Users\\User\\Desktop\";\n" +
                "            Console.WriteLine(path == verbatimPath);  // => true\n" +
                "\n" +
                "            // You can split a string over two lines with the @ symbol. To escape \" use \"\"\n" +
                "            string bazString = @\"Here's some stuff\n" +
                "on a new line! \"\"Wow!\"\", the masses cried\";\n" +
                "\n" +
                "            // Use const or read-only to make a variable immutable\n" +
                "            // const values are calculated at compile time\n" +
                "            const int HoursWorkPerWeek = 9001;\n" +
                "\n" +
                "            ///////////////////////////////////////////////////\n" +
                "            // Data Structures\n" +
                "            ///////////////////////////////////////////////////\n" +
                "\n" +
                "            // Arrays - zero indexed\n" +
                "            // The array size must be decided upon declaration\n" +
                "            // The format for declaring an array is follows:\n" +
                "            // <datatype>[] <var name> = new <datatype>[<array size>];\n" +
                "            int[] intArray = new int[10];\n" +
                "\n" +
                "            // Another way to declare & initialize an array\n" +
                "            int[] y = { 9000, 1000, 1337 };\n" +
                "\n" +
                "            // Indexing an array - Accessing an element\n" +
                "            Console.WriteLine(\"intArray @ 0: \" + intArray[0]);\n" +
                "            // Arrays are mutable.\n" +
                "            intArray[1] = 1;\n" +
                "\n" +
                "            // Lists\n" +
                "            // Lists are used more frequently than arrays as they are more flexible\n" +
                "            // The format for declaring a list is follows:\n" +
                "            // List<datatype> <var name> = new List<datatype>();\n" +
                "            List<int> intList = new List<int>();\n" +
                "            List<string> stringList = new List<string>();\n" +
                "            List<int> z = new List<int> { 9000, 1000, 1337 }; // initialize\n" +
                "            // The <> are for generics - Check out the cool stuff section\n" +
                "\n" +
                "            // Lists don't default to a value;\n" +
                "            // A value must be added before accessing the index\n" +
                "            intList.Add(1);\n" +
                "            Console.WriteLine(\"intList @ 0: \" + intList[0]);\n" +
                "\n" +
                "            // Others data structures to check out:\n" +
                "            // Stack/Queue\n" +
                "            // Dictionary (an implementation of a hash map)\n" +
                "            // HashSet\n" +
                "            // Read-only Collections\n" +
                "            // Tuple (.Net 4+)\n" +
                "\n" +
                "            ///////////////////////////////////////\n" +
                "            // Operators\n" +
                "            ///////////////////////////////////////\n" +
                "            Console.WriteLine(\"\\n->Operators\");\n" +
                "\n" +
                "            int i1 = 1, i2 = 2; // Shorthand for multiple declarations\n" +
                "\n" +
                "            // Arithmetic is straightforward\n" +
                "            Console.WriteLine(i1 + i2 - i1 * 3 / 7); // => 3\n" +
                "\n" +
                "            // Modulo\n" +
                "            Console.WriteLine(\"11%3 = \" + (11 % 3)); // => 2\n" +
                "\n" +
                "            // Comparison operators\n" +
                "            Console.WriteLine(\"3 == 2? \" + (3 == 2)); // => false\n" +
                "            Console.WriteLine(\"3 != 2? \" + (3 != 2)); // => true\n" +
                "            Console.WriteLine(\"3 > 2? \" + (3 > 2)); // => true\n" +
                "            Console.WriteLine(\"3 < 2? \" + (3 < 2)); // => false\n" +
                "            Console.WriteLine(\"2 <= 2? \" + (2 <= 2)); // => true\n" +
                "            Console.WriteLine(\"2 >= 2? \" + (2 >= 2)); // => true\n" +
                "\n" +
                "            // Bitwise operators!\n" +
                "            /*\n" +
                "            ~       Unary bitwise complement\n" +
                "            <<      Signed left shift\n" +
                "            >>      Signed right shift\n" +
                "            &       Bitwise AND\n" +
                "            ^       Bitwise exclusive OR\n" +
                "            |       Bitwise inclusive OR\n" +
                "            */\n" +
                "\n" +
                "            // Incrementations\n" +
                "            int i = 0;\n" +
                "            Console.WriteLine(\"\\n->Inc/Dec-rementation\");\n" +
                "            Console.WriteLine(i++); //Prints \"0\", i = 1. Post-Incrementation\n" +
                "            Console.WriteLine(++i); //Prints \"2\", i = 2. Pre-Incrementation\n" +
                "            Console.WriteLine(i--); //Prints \"2\", i = 1. Post-Decrementation\n" +
                "            Console.WriteLine(--i); //Prints \"0\", i = 0. Pre-Decrementation\n" +
                "\n" +
                "            ///////////////////////////////////////\n" +
                "            // Control Structures\n" +
                "            ///////////////////////////////////////\n" +
                "            Console.WriteLine(\"\\n->Control Structures\");\n" +
                "\n" +
                "            // If statements are c-like\n" +
                "            int j = 10;\n" +
                "            if (j == 10)\n" +
                "            {\n" +
                "                Console.WriteLine(\"I get printed\");\n" +
                "            }\n" +
                "            else if (j > 10)\n" +
                "            {\n" +
                "                Console.WriteLine(\"I don't\");\n" +
                "            }\n" +
                "            else\n" +
                "            {\n" +
                "                Console.WriteLine(\"I also don't\");\n" +
                "            }\n" +
                "\n" +
                "            // Ternary operators\n" +
                "            // A simple if/else can be written as follows\n" +
                "            // <condition> ? <true> : <false>\n" +
                "            int toCompare = 17;\n" +
                "            string isTrue = toCompare == 17 ? \"True\" : \"False\";\n" +
                "\n" +
                "            // While loop\n" +
                "            int fooWhile = 0;\n" +
                "            while (fooWhile < 100)\n" +
                "            {\n" +
                "                // Iterated 100 times, fooWhile 0->99\n" +
                "                fooWhile++;\n" +
                "            }\n" +
                "\n" +
                "            // Do While Loop\n" +
                "            int fooDoWhile = 0;\n" +
                "            do\n" +
                "            {\n" +
                "                // Start iteration 100 times, fooDoWhile 0->99\n" +
                "                if (false)\n" +
                "                    continue; // skip the current iteration\n" +
                "\n" +
                "                fooDoWhile++;\n" +
                "\n" +
                "                if (fooDoWhile == 50)\n" +
                "                    break; // breaks from the loop completely\n" +
                "\n" +
                "            } while (fooDoWhile < 100);\n" +
                "\n" +
                "            // for loop structure => for(<start_statement>; <conditional>; <step>)\n" +
                "            for (int fooFor = 0; fooFor < 10; fooFor++)\n" +
                "            {\n" +
                "                // Iterated 10 times, fooFor 0->9\n" +
                "            }\n" +
                "\n" +
                "            // For Each Loop\n" +
                "            // foreach loop structure => foreach(<iteratorType> <iteratorName> in <enumerable>)\n" +
                "            // The foreach loop loops over any object implementing IEnumerable or IEnumerable<T>\n" +
                "            // All the collection types (Array, List, Dictionary...) in the .Net framework\n" +
                "            // implement one or both of these interfaces.\n" +
                "            // (The ToCharArray() could be removed, because a string also implements IEnumerable)\n" +
                "            foreach (char character in \"Hello World\".ToCharArray())\n" +
                "            {\n" +
                "                // Iterated over all the characters in the string\n" +
                "            }\n" +
                "\n" +
                "            // Switch Case\n" +
                "            // A switch works with the byte, short, char, and int data types.\n" +
                "            // It also works with enumerated types (discussed in Enum Types),\n" +
                "            // the String class, and a few special classes that wrap\n" +
                "            // primitive types: Character, Byte, Short, and Integer.\n" +
                "            int month = 3;\n" +
                "            string monthString;\n" +
                "            switch (month)\n" +
                "            {\n" +
                "                case 1:\n" +
                "                    monthString = \"January\";\n" +
                "                    break;\n" +
                "                case 2:\n" +
                "                    monthString = \"February\";\n" +
                "                    break;\n" +
                "                case 3:\n" +
                "                    monthString = \"March\";\n" +
                "                    break;\n" +
                "                // You can assign more than one case to an action\n" +
                "                // But you can't add an action without a break before another case\n" +
                "                // (if you want to do this, you would have to explicitly add a goto case x\n" +
                "                case 6:\n" +
                "                case 7:\n" +
                "                case 8:\n" +
                "                    monthString = \"Summer time!!\";\n" +
                "                    break;\n" +
                "                default:\n" +
                "                    monthString = \"Some other month\";\n" +
                "                    break;\n" +
                "            }\n" +
                "\n" +
                "            ///////////////////////////////////////\n" +
                "            // Converting Data Types And Typecasting\n" +
                "            ///////////////////////////////////////\n" +
                "\n" +
                "            // Converting data\n" +
                "\n" +
                "            // Convert String To Integer\n" +
                "            // this will throw a FormatException on failure\n" +
                "            int.Parse(\"123\"); // returns an integer version of \"123\"\n" +
                "\n" +
                "            // try parse will default to type default on failure\n" +
                "            // in this case: 0\n" +
                "            int tryInt;\n" +
                "            if (int.TryParse(\"123\", out tryInt)) // Function is boolean\n" +
                "                Console.WriteLine(tryInt);       // 123\n" +
                "\n" +
                "            // Convert Integer To String\n" +
                "            // Convert class has a number of methods to facilitate conversions\n" +
                "\n" +
                "            // String to int\n" +
                "\n" +
                "            // Better\n" +
                "            bool result = int.TryParse(string, out var integer)\n" +
                "            int.Parse(string);\n" +
                "\n" +
                "            // Not recommended\n" +
                "            Convert.ToString(123);\n" +
                "\n" +
                "            // Int to string\n" +
                "            tryInt.ToString();\n" +
                "\n" +
                "            // Casting\n" +
                "            // Cast decimal 15 to an int\n" +
                "            // and then implicitly cast to long\n" +
                "            long x = (int) 15M;\n" +
                "        }\n" +
                "\n" +
                "        ///////////////////////////////////////\n" +
                "        // CLASSES - see definitions at end of file\n" +
                "        ///////////////////////////////////////\n" +
                "        public static void Classes()\n" +
                "        {\n" +
                "            // See Declaration of objects at end of file\n" +
                "\n" +
                "            // Use new to instantiate a class\n" +
                "            Bicycle trek = new Bicycle();\n" +
                "\n" +
                "            // Call object methods\n" +
                "            trek.SpeedUp(3); // You should always use setter and getter methods\n" +
                "            trek.Cadence = 100;\n" +
                "\n" +
                "            // ToString is a convention to display the value of this Object.\n" +
                "            Console.WriteLine(\"trek info: \" + trek.Info());\n" +
                "\n" +
                "            // Instantiate a new Penny Farthing\n" +
                "            PennyFarthing funbike = new PennyFarthing(1, 10);\n" +
                "            Console.WriteLine(\"funbike info: \" + funbike.Info());\n" +
                "\n" +
                "            Console.Read();\n" +
                "        } // End main method\n" +
                "\n" +
                "        // Available in C# 9 and later, this is basically a syntactic sugar for a class. Records are immutable*.\n" +
                "        public record ARecord(string Csharp);\n" +
                "\n" +
                "        // CONSOLE ENTRY - A console application must have a main method as an entry point\n" +
                "        public static void Main(string[] args)\n" +
                "        {\n" +
                "            OtherInterestingFeatures();\n" +
                "        }\n" +
                "\n" +
                "        //\n" +
                "        // INTERESTING FEATURES\n" +
                "        //\n" +
                "\n" +
                "        // DEFAULT METHOD SIGNATURES\n" +
                "\n" +
                "        public // Visibility\n" +
                "        static // Allows for direct call on class without object\n" +
                "        int // Return Type,\n" +
                "        MethodSignatures(\n" +
                "            int maxCount, // First variable, expects an int\n" +
                "            int count = 0, // will default the value to 0 if not passed in\n" +
                "            int another = 3,\n" +
                "            params string[] otherParams // captures all other parameters passed to method\n" +
                "        )\n" +
                "        {\n" +
                "            return -1;\n" +
                "        }\n" +
                "\n" +
                "        // Methods can have the same name, as long as the signature is unique\n" +
                "        // A method that differs only in return type is not unique\n" +
                "        public static void MethodSignatures(\n" +
                "            ref int maxCount, // Pass by reference\n" +
                "            out int count)\n" +
                "        {\n" +
                "            // the argument passed in as 'count' will hold the value of 15 outside of this function\n" +
                "            count = 15; // out param must be assigned before control leaves the method\n" +
                "        }\n" +
                "\n" +
                "        // GENERICS\n" +
                "        // The classes for TKey and TValue is specified by the user calling this function.\n" +
                "        // This method emulates the SetDefault of Python\n" +
                "        public static TValue SetDefault<TKey, TValue>(\n" +
                "            IDictionary<TKey, TValue> dictionary,\n" +
                "            TKey key,\n" +
                "            TValue defaultItem)\n" +
                "        {\n" +
                "            TValue result;\n" +
                "            if (!dictionary.TryGetValue(key, out result))\n" +
                "                return dictionary[key] = defaultItem;\n" +
                "            return result;\n" +
                "        }\n" +
                "\n" +
                "        // You can narrow down the objects that are passed in\n" +
                "        public static void IterateAndPrint<T>(T toPrint) where T: IEnumerable<int>\n" +
                "        {\n" +
                "            // We can iterate, since T is a IEnumerable\n" +
                "            foreach (var item in toPrint)\n" +
                "                // Item is an int\n" +
                "                Console.WriteLine(item.ToString());\n" +
                "        }\n" +
                "\n" +
                "        // YIELD\n" +
                "        // Usage of the \"yield\" keyword indicates that the method it appears in is an Iterator\n" +
                "        // (this means you can use it in a foreach loop)\n" +
                "        public static IEnumerable<int> YieldCounter(int limit = 10)\n" +
                "        {\n" +
                "            for (var i = 0; i < limit; i++)\n" +
                "                yield return i;\n" +
                "        }\n" +
                "\n" +
                "        // which you would call like this :\n" +
                "        public static void PrintYieldCounterToConsole()\n" +
                "        {\n" +
                "            foreach (var counter in YieldCounter())\n" +
                "                Console.WriteLine(counter);\n" +
                "        }\n" +
                "\n" +
                "        // you can use more than one \"yield return\" in a method\n" +
                "        public static IEnumerable<int> ManyYieldCounter()\n" +
                "        {\n" +
                "            yield return 0;\n" +
                "            yield return 1;\n" +
                "            yield return 2;\n" +
                "            yield return 3;\n" +
                "        }\n" +
                "\n" +
                "        // you can also use \"yield break\" to stop the Iterator\n" +
                "        // this method would only return half of the values from 0 to limit.\n" +
                "        public static IEnumerable<int> YieldCounterWithBreak(int limit = 10)\n" +
                "        {\n" +
                "            for (var i = 0; i < limit; i++)\n" +
                "            {\n" +
                "                if (i > limit/2) yield break;\n" +
                "                yield return i;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        public static void OtherInterestingFeatures()\n" +
                "        {\n" +
                "            // OPTIONAL PARAMETERS\n" +
                "            MethodSignatures(3, 1, 3, \"Some\", \"Extra\", \"Strings\");\n" +
                "            MethodSignatures(3, another: 3); // explicitly set a parameter, skipping optional ones\n" +
                "\n" +
                "            // BY REF AND OUT PARAMETERS\n" +
                "            int maxCount = 0, count; // ref params must have value\n" +
                "            MethodSignatures(ref maxCount, out count);\n" +
                "\n" +
                "            // EXTENSION METHODS\n" +
                "            int i = 3;\n" +
                "            i.Print(); // Defined below\n" +
                "\n" +
                "            // NULLABLE TYPES - great for database interaction / return values\n" +
                "            // any value type (i.e. not a class) can be made nullable by suffixing a ?\n" +
                "            // <type>? <var name> = <value>\n" +
                "            int? nullable = null; // short hand for Nullable<int>\n" +
                "            Console.WriteLine(\"Nullable variable: \" + nullable);\n" +
                "            bool hasValue = nullable.HasValue; // true if not null\n" +
                "\n" +
                "            // ?? is syntactic sugar for specifying default value (coalesce)\n" +
                "            // in case variable is null\n" +
                "            int notNullable = nullable ?? 0; // 0\n" +
                "\n" +
                "            // ?. is an operator for null-propagation - a shorthand way of checking for null\n" +
                "            nullable?.Print(); // Use the Print() extension method if nullable isn't null\n" +
                "\n" +
                "            // IMPLICITLY TYPED VARIABLES - you can let the compiler work out what the type is:\n" +
                "            var magic = \"magic is a string, at compile time, so you still get type safety\";\n" +
                "            // magic = 9; will not work as magic is a string, not an int\n" +
                "\n" +
                "            // GENERICS\n" +
                "            //\n" +
                "            var phonebook = new Dictionary<string, string>() {\n" +
                "                {\"Sarah\", \"212 555 5555\"} // Add some entries to the phone book\n" +
                "            };\n" +
                "\n" +
                "            // Calling SETDEFAULT defined as a generic above\n" +
                "            Console.WriteLine(SetDefault<string,string>(phonebook, \"Shaun\", \"No Phone\")); // No Phone\n" +
                "            // nb, you don't need to specify the TKey and TValue since they can be\n" +
                "            // derived implicitly\n" +
                "            Console.WriteLine(SetDefault(phonebook, \"Sarah\", \"No Phone\")); // 212 555 5555\n" +
                "\n" +
                "            // LAMBDA EXPRESSIONS - allow you to write code in line\n" +
                "            Func<int, int> square = (x) => x * x; // Last T item is the return value\n" +
                "            Console.WriteLine(square(3)); // 9\n" +
                "\n" +
                "            // ERROR HANDLING - coping with an uncertain world\n" +
                "            try\n" +
                "            {\n" +
                "                var funBike = PennyFarthing.CreateWithGears(6);\n" +
                "\n" +
                "                // will no longer execute because CreateWithGears throws an exception\n" +
                "                string some = \"\";\n" +
                "                if (true) some = null;\n" +
                "                some.ToLower(); // throws a NullReferenceException\n" +
                "            }\n" +
                "            catch (NotSupportedException)\n" +
                "            {\n" +
                "                Console.WriteLine(\"Not so much fun now!\");\n" +
                "            }\n" +
                "            catch (Exception ex) // catch all other exceptions\n" +
                "            {\n" +
                "                throw new ApplicationException(\"It hit the fan\", ex);\n" +
                "                // throw; // A rethrow that preserves the callstack\n" +
                "            }\n" +
                "            // catch { } // catch-all without capturing the Exception\n" +
                "            finally\n" +
                "            {\n" +
                "                // executes after try or catch\n" +
                "            }\n" +
                "\n" +
                "            // DISPOSABLE RESOURCES MANAGEMENT - let you handle unmanaged resources easily.\n" +
                "            // Most of objects that access unmanaged resources (file handle, device contexts, etc.)\n" +
                "            // implement the IDisposable interface. The using statement takes care of\n" +
                "            // cleaning those IDisposable objects for you.\n" +
                "            using (StreamWriter writer = new StreamWriter(\"log.txt\"))\n" +
                "            {\n" +
                "                writer.WriteLine(\"Nothing suspicious here\");\n" +
                "                // At the end of scope, resources will be released.\n" +
                "                // Even if an exception is thrown.\n" +
                "            }\n" +
                "\n" +
                "            // PARALLEL FRAMEWORK\n" +
                "            // https://devblogs.microsoft.com/csharpfaq/parallel-programming-in-net-framework-4-getting-started/\n" +
                "\n" +
                "            var words = new List<string> {\"dog\", \"cat\", \"horse\", \"pony\"};\n" +
                "\n" +
                "            Parallel.ForEach(words,\n" +
                "                new ParallelOptions() { MaxDegreeOfParallelism = 4 },\n" +
                "                word =>\n" +
                "                {\n" +
                "                    Console.WriteLine(word);\n" +
                "                }\n" +
                "            );\n" +
                "\n" +
                "            // Running this will produce different outputs\n" +
                "            // since each thread finishes at different times.\n" +
                "            // Some example outputs are:\n" +
                "            // cat dog horse pony\n" +
                "            // dog horse pony cat\n" +
                "\n" +
                "            // DYNAMIC OBJECTS (great for working with other languages)\n" +
                "            dynamic student = new ExpandoObject();\n" +
                "            student.FirstName = \"First Name\"; // No need to define class first!\n" +
                "\n" +
                "            // You can even add methods (returns a string, and takes in a string)\n" +
                "            student.Introduce = new Func<string, string>(\n" +
                "                (introduceTo) => string.Format(\"Hey {0}, this is {1}\", student.FirstName, introduceTo));\n" +
                "            Console.WriteLine(student.Introduce(\"Beth\"));\n" +
                "\n" +
                "            // IQUERYABLE<T> - almost all collections implement this, which gives you a lot of\n" +
                "            // very useful Map / Filter / Reduce style methods\n" +
                "            var bikes = new List<Bicycle>();\n" +
                "            bikes.Sort(); // Sorts the array\n" +
                "            bikes.Sort((b1, b2) => b1.Wheels.CompareTo(b2.Wheels)); // Sorts based on wheels\n" +
                "            var result = bikes\n" +
                "                .Where(b => b.Wheels > 3) // Filters - chainable (returns IQueryable of previous type)\n" +
                "                .Where(b => b.IsBroken && b.HasTassles)\n" +
                "                .Select(b => b.ToString()); // Map - we only this selects, so result is a IQueryable<string>\n" +
                "\n" +
                "            var sum = bikes.Sum(b => b.Wheels); // Reduce - sums all the wheels in the collection\n" +
                "\n" +
                "            // Create a list of IMPLICIT objects based on some parameters of the bike\n" +
                "            var bikeSummaries = bikes.Select(b=>new { Name = b.Name, IsAwesome = !b.IsBroken && b.HasTassles });\n" +
                "            // Hard to show here, but you get type ahead completion since the compiler can implicitly work\n" +
                "            // out the types above!\n" +
                "            foreach (var bikeSummary in bikeSummaries.Where(b => b.IsAwesome))\n" +
                "                Console.WriteLine(bikeSummary.Name);\n" +
                "\n" +
                "            // ASPARALLEL\n" +
                "            // And this is where things get wicked - combine linq and parallel operations\n" +
                "            var threeWheelers = bikes.AsParallel().Where(b => b.Wheels == 3).Select(b => b.Name);\n" +
                "            // this will happen in parallel! Threads will automagically be spun up and the\n" +
                "            // results divvied amongst them! Amazing for large datasets when you have lots of\n" +
                "            // cores\n" +
                "\n" +
                "            // LINQ - maps a store to IQueryable<T> objects, with delayed execution\n" +
                "            // e.g. LinqToSql - maps to a database, LinqToXml maps to an xml document\n" +
                "            var db = new BikeRepository();\n" +
                "\n" +
                "            // execution is delayed, which is great when querying a database\n" +
                "            var filter = db.Bikes.Where(b => b.HasTassles); // no query run\n" +
                "            if (42 > 6) // You can keep adding filters, even conditionally - great for \"advanced search\" functionality\n" +
                "                filter = filter.Where(b => b.IsBroken); // no query run\n" +
                "\n" +
                "            var query = filter\n" +
                "                .OrderBy(b => b.Wheels)\n" +
                "                .ThenBy(b => b.Name)\n" +
                "                .Select(b => b.Name); // still no query run\n" +
                "\n" +
                "            // Now the query runs, but opens a reader, so only populates as you iterate through\n" +
                "            foreach (string bike in query)\n" +
                "                Console.WriteLine(result);\n" +
                "\n" +
                "\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "    } // End LearnCSharp class\n" +
                "\n" +
                "    // You can include other classes in a .cs file\n" +
                "\n" +
                "    public static class Extensions\n" +
                "    {\n" +
                "        // EXTENSION METHODS\n" +
                "        public static void Print(this object obj)\n" +
                "        {\n" +
                "            Console.WriteLine(obj.ToString());\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    // DELEGATES AND EVENTS\n" +
                "    public class DelegateTest\n" +
                "    {\n" +
                "        public static int count = 0;\n" +
                "        public static int Increment()\n" +
                "        {\n" +
                "            // increment count then return it\n" +
                "            return ++count;\n" +
                "        }\n" +
                "\n" +
                "        // A delegate is a reference to a method.\n" +
                "        // To reference the Increment method,\n" +
                "        // first declare a delegate with the same signature,\n" +
                "        // i.e. takes no arguments and returns an int\n" +
                "        public delegate int IncrementDelegate();\n" +
                "\n" +
                "        // An event can also be used to trigger delegates\n" +
                "        // Create an event with the delegate type\n" +
                "        public static event IncrementDelegate MyEvent;\n" +
                "\n" +
                "        static void Main(string[] args)\n" +
                "        {\n" +
                "            // Refer to the Increment method by instantiating the delegate\n" +
                "            // and passing the method itself in as an argument\n" +
                "            IncrementDelegate inc = new IncrementDelegate(Increment);\n" +
                "            Console.WriteLine(inc());  // => 1\n" +
                "\n" +
                "            // Delegates can be composed with the + operator\n" +
                "            IncrementDelegate composedInc = inc;\n" +
                "            composedInc += inc;\n" +
                "            composedInc += inc;\n" +
                "\n" +
                "            // composedInc will run Increment 3 times\n" +
                "            Console.WriteLine(composedInc());  // => 4\n" +
                "\n" +
                "\n" +
                "            // Subscribe to the event with the delegate\n" +
                "            MyEvent += new IncrementDelegate(Increment);\n" +
                "            MyEvent += new IncrementDelegate(Increment);\n" +
                "\n" +
                "            // Trigger the event\n" +
                "            // ie. run all delegates subscribed to this event\n" +
                "            Console.WriteLine(MyEvent());  // => 6\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    // Class Declaration Syntax:\n" +
                "    // <public/private/protected/internal> class <class name>{\n" +
                "    //    //data fields, constructors, functions all inside.\n" +
                "    //    //functions are called as methods in Java.\n" +
                "    // }\n" +
                "\n" +
                "    public class Bicycle\n" +
                "    {\n" +
                "        // Bicycle's Fields/Variables\n" +
                "        public int Cadence // Public: Can be accessed from anywhere\n" +
                "        {\n" +
                "            get // get - define a method to retrieve the property\n" +
                "            {\n" +
                "                return _cadence;\n" +
                "            }\n" +
                "            set // set - define a method to set a property\n" +
                "            {\n" +
                "                _cadence = value; // Value is the value passed in to the setter\n" +
                "            }\n" +
                "        }\n" +
                "        private int _cadence;\n" +
                "\n" +
                "        protected virtual int Gear // Protected: Accessible from the class and subclasses\n" +
                "        {\n" +
                "            get; // creates an auto property so you don't need a member field\n" +
                "            set;\n" +
                "        }\n" +
                "\n" +
                "        internal int Wheels // Internal: Accessible from within the assembly\n" +
                "        {\n" +
                "            get;\n" +
                "            private set; // You can set modifiers on the get/set methods\n" +
                "        }\n" +
                "\n" +
                "        int _speed; // Everything is private by default: Only accessible from within this class.\n" +
                "                    // can also use keyword private\n" +
                "        public string Name { get; set; }\n" +
                "\n" +
                "        // Properties also have a special syntax for when you want a readonly property\n" +
                "        // that simply returns the result of an expression\n" +
                "        public string LongName => Name + \" \" + _speed + \" speed\";\n" +
                "\n" +
                "        // Enum is a value type that consists of a set of named constants\n" +
                "        // It is really just mapping a name to a value (an int, unless specified otherwise).\n" +
                "        // The approved types for an enum are byte, sbyte, short, ushort, int, uint, long, or ulong.\n" +
                "        // An enum can't contain the same value twice.\n" +
                "        public enum BikeBrand\n" +
                "        {\n" +
                "            AIST,\n" +
                "            BMC,\n" +
                "            Electra = 42, //you can explicitly set a value to a name\n" +
                "            Gitane // 43\n" +
                "        }\n" +
                "        // We defined this type inside a Bicycle class, so it is a nested type\n" +
                "        // Code outside of this class should reference this type as Bicycle.BikeBrand\n" +
                "\n" +
                "        public BikeBrand Brand; // After declaring an enum type, we can declare the field of this type\n" +
                "\n" +
                "        // Decorate an enum with the FlagsAttribute to indicate that multiple values can be switched on\n" +
                "        // Any class derived from Attribute can be used to decorate types, methods, parameters etc\n" +
                "        // Bitwise operators & and | can be used to perform and/or operations\n" +
                "\n" +
                "        [Flags]\n" +
                "        public enum BikeAccessories\n" +
                "        {\n" +
                "            None = 0,\n" +
                "            Bell = 1,\n" +
                "            MudGuards = 2, // need to set the values manually!\n" +
                "            Racks = 4,\n" +
                "            Lights = 8,\n" +
                "            FullPackage = Bell | MudGuards | Racks | Lights\n" +
                "        }\n" +
                "\n" +
                "        // Usage: aBike.Accessories.HasFlag(Bicycle.BikeAccessories.Bell)\n" +
                "        // Before .NET 4: (aBike.Accessories & Bicycle.BikeAccessories.Bell) == Bicycle.BikeAccessories.Bell\n" +
                "        public BikeAccessories Accessories { get; set; }\n" +
                "\n" +
                "        // Static members belong to the type itself rather than specific object.\n" +
                "        // You can access them without a reference to any object:\n" +
                "        // Console.WriteLine(\"Bicycles created: \" + Bicycle.bicyclesCreated);\n" +
                "        public static int BicyclesCreated { get; set; }\n" +
                "\n" +
                "        // readonly values are set at run time\n" +
                "        // they can only be assigned upon declaration or in a constructor\n" +
                "        readonly bool _hasCardsInSpokes = false; // read-only private\n" +
                "\n" +
                "        // Constructors are a way of creating classes\n" +
                "        // This is a default constructor\n" +
                "        public Bicycle()\n" +
                "        {\n" +
                "            this.Gear = 1; // you can access members of the object with the keyword this\n" +
                "            Cadence = 50;  // but you don't always need it\n" +
                "            _speed = 5;\n" +
                "            Name = \"Bontrager\";\n" +
                "            Brand = BikeBrand.AIST;\n" +
                "            BicyclesCreated++;\n" +
                "        }\n" +
                "\n" +
                "        // This is a specified constructor (it contains arguments)\n" +
                "        public Bicycle(int startCadence, int startSpeed, int startGear,\n" +
                "                       string name, bool hasCardsInSpokes, BikeBrand brand)\n" +
                "            : base() // calls base first\n" +
                "        {\n" +
                "            Gear = startGear;\n" +
                "            Cadence = startCadence;\n" +
                "            _speed = startSpeed;\n" +
                "            Name = name;\n" +
                "            _hasCardsInSpokes = hasCardsInSpokes;\n" +
                "            Brand = brand;\n" +
                "        }\n" +
                "\n" +
                "        // Constructors can be chained\n" +
                "        public Bicycle(int startCadence, int startSpeed, BikeBrand brand) :\n" +
                "            this(startCadence, startSpeed, 0, \"big wheels\", true, brand)\n" +
                "        {\n" +
                "        }\n" +
                "\n" +
                "        // Function Syntax:\n" +
                "        // <public/private/protected> <return type> <function name>(<args>)\n" +
                "\n" +
                "        // classes can implement getters and setters for their fields\n" +
                "        // or they can implement properties (this is the preferred way in C#)\n" +
                "\n" +
                "        // Method parameters can have default values.\n" +
                "        // In this case, methods can be called with these parameters omitted\n" +
                "        public void SpeedUp(int increment = 1)\n" +
                "        {\n" +
                "            _speed += increment;\n" +
                "        }\n" +
                "\n" +
                "        public void SlowDown(int decrement = 1)\n" +
                "        {\n" +
                "            _speed -= decrement;\n" +
                "        }\n" +
                "\n" +
                "        // properties get/set values\n" +
                "        // when only data needs to be accessed, consider using properties.\n" +
                "        // properties may have either get or set, or both\n" +
                "        private bool _hasTassles; // private variable\n" +
                "        public bool HasTassles // public accessor\n" +
                "        {\n" +
                "            get { return _hasTassles; }\n" +
                "            set { _hasTassles = value; }\n" +
                "        }\n" +
                "\n" +
                "        // You can also define an automatic property in one line\n" +
                "        // this syntax will create a backing field automatically.\n" +
                "        // You can set an access modifier on either the getter or the setter (or both)\n" +
                "        // to restrict its access:\n" +
                "        public bool IsBroken { get; private set; }\n" +
                "\n" +
                "        // Properties can be auto-implemented\n" +
                "        public int FrameSize\n" +
                "        {\n" +
                "            get;\n" +
                "            // you are able to specify access modifiers for either get or set\n" +
                "            // this means only Bicycle class can call set on Framesize\n" +
                "            private set;\n" +
                "        }\n" +
                "\n" +
                "        // It's also possible to define custom Indexers on objects.\n" +
                "        // All though this is not entirely useful in this example, you\n" +
                "        // could do bicycle[0] which returns \"chris\" to get the first passenger or\n" +
                "        // bicycle[1] = \"lisa\" to set the passenger. (of this apparent quattrocycle)\n" +
                "        private string[] passengers = { \"chris\", \"phil\", \"darren\", \"regina\" };\n" +
                "\n" +
                "        public string this[int i]\n" +
                "        {\n" +
                "            get {\n" +
                "                return passengers[i];\n" +
                "            }\n" +
                "\n" +
                "            set {\n" +
                "                passengers[i] = value;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        // Method to display the attribute values of this Object.\n" +
                "        public virtual string Info()\n" +
                "        {\n" +
                "            return \"Gear: \" + Gear +\n" +
                "                    \" Cadence: \" + Cadence +\n" +
                "                    \" Speed: \" + _speed +\n" +
                "                    \" Name: \" + Name +\n" +
                "                    \" Cards in Spokes: \" + (_hasCardsInSpokes ? \"yes\" : \"no\") +\n" +
                "                    \"\\n------------------------------\\n\"\n" +
                "                    ;\n" +
                "        }\n" +
                "\n" +
                "        // Methods can also be static. It can be useful for helper methods\n" +
                "        public static bool DidWeCreateEnoughBicycles()\n" +
                "        {\n" +
                "            // Within a static method, we only can reference static class members\n" +
                "            return BicyclesCreated > 9000;\n" +
                "        } // If your class only needs static members, consider marking the class itself as static.\n" +
                "\n" +
                "\n" +
                "    } // end class Bicycle\n" +
                "\n" +
                "    // PennyFarthing is a subclass of Bicycle\n" +
                "    class PennyFarthing : Bicycle\n" +
                "    {\n" +
                "        // (Penny Farthings are those bicycles with the big front wheel.\n" +
                "        // They have no gears.)\n" +
                "\n" +
                "        // calling parent constructor\n" +
                "        public PennyFarthing(int startCadence, int startSpeed) :\n" +
                "            base(startCadence, startSpeed, 0, \"PennyFarthing\", true, BikeBrand.Electra)\n" +
                "        {\n" +
                "        }\n" +
                "\n" +
                "        protected override int Gear\n" +
                "        {\n" +
                "            get\n" +
                "            {\n" +
                "                return 0;\n" +
                "            }\n" +
                "            set\n" +
                "            {\n" +
                "                throw new InvalidOperationException(\"You can't change gears on a PennyFarthing\");\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        public static PennyFarthing CreateWithGears(int gears)\n" +
                "        {\n" +
                "            var penny = new PennyFarthing(1, 1);\n" +
                "            penny.Gear = gears; // Oops, can't do this!\n" +
                "            return penny;\n" +
                "        }\n" +
                "\n" +
                "        public override string Info()\n" +
                "        {\n" +
                "            string result = \"PennyFarthing bicycle \";\n" +
                "            result += base.ToString(); // Calling the base version of the method\n" +
                "            return result;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    // Interfaces only contain signatures of the members, without the implementation.\n" +
                "    interface IJumpable\n" +
                "    {\n" +
                "        void Jump(int meters); // all interface members are implicitly public\n" +
                "    }\n" +
                "\n" +
                "    interface IBreakable\n" +
                "    {\n" +
                "        bool Broken { get; } // interfaces can contain properties as well as methods & events\n" +
                "    }\n" +
                "\n" +
                "    // Classes can inherit only one other class, but can implement any amount of interfaces,\n" +
                "    // however the base class name must be the first in the list and all interfaces follow\n" +
                "    class MountainBike : Bicycle, IJumpable, IBreakable\n" +
                "    {\n" +
                "        int damage = 0;\n" +
                "\n" +
                "        public void Jump(int meters)\n" +
                "        {\n" +
                "            damage += meters;\n" +
                "        }\n" +
                "\n" +
                "        public bool Broken\n" +
                "        {\n" +
                "            get\n" +
                "            {\n" +
                "                return damage > 100;\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    /// <summary>\n" +
                "    /// Used to connect to DB for LinqToSql example.\n" +
                "    /// EntityFramework Code First is awesome (similar to Ruby's ActiveRecord, but bidirectional)\n" +
                "    /// https://docs.microsoft.com/ef/ef6/modeling/code-first/workflows/new-database\n" +
                "    /// </summary>\n" +
                "    public class BikeRepository : DbContext\n" +
                "    {\n" +
                "        public BikeRepository()\n" +
                "            : base()\n" +
                "        {\n" +
                "        }\n" +
                "\n" +
                "        public DbSet<Bicycle> Bikes { get; set; }\n" +
                "    }\n" +
                "\n" +
                "    // Classes can be split across multiple .cs files\n" +
                "    // A1.cs\n" +
                "    public partial class A\n" +
                "    {\n" +
                "        public static void A1()\n" +
                "        {\n" +
                "            Console.WriteLine(\"Method A1 in class A\");\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    // A2.cs\n" +
                "    public partial class A\n" +
                "    {\n" +
                "        public static void A2()\n" +
                "        {\n" +
                "            Console.WriteLine(\"Method A2 in class A\");\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    // Program using the partial class \"A\"\n" +
                "    public class Program\n" +
                "    {\n" +
                "        static void Main()\n" +
                "        {\n" +
                "            A.A1();\n" +
                "            A.A2();\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    // String interpolation by prefixing the string with $\n" +
                "    // and wrapping the expression you want to interpolate with { braces }\n" +
                "    // You can also combine both interpolated and verbatim strings with $@\n" +
                "    public class Rectangle\n" +
                "    {\n" +
                "        public int Length { get; set; }\n" +
                "        public int Width { get; set; }\n" +
                "    }\n" +
                "\n" +
                "    class Program\n" +
                "    {\n" +
                "        static void Main(string[] args)\n" +
                "        {\n" +
                "            Rectangle rect = new Rectangle { Length = 5, Width = 3 };\n" +
                "            Console.WriteLine($\"The length is {rect.Length} and the width is {rect.Width}\");\n" +
                "\n" +
                "            string username = \"User\";\n" +
                "            Console.WriteLine($@\"C:\\Users\\{username}\\Desktop\");\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    // New C# 6 features\n" +
                "    class GlassBall : IJumpable, IBreakable\n" +
                "    {\n" +
                "        // Autoproperty initializers\n" +
                "        public int Damage { get; private set; } = 0;\n" +
                "\n" +
                "        // Autoproperty initializers on getter-only properties\n" +
                "        public string Name { get; } = \"Glass ball\";\n" +
                "\n" +
                "        // Getter-only autoproperty that is initialized in constructor\n" +
                "        public string GenieName { get; }\n" +
                "\n" +
                "        public GlassBall(string genieName = null)\n" +
                "        {\n" +
                "            GenieName = genieName;\n" +
                "        }\n" +
                "\n" +
                "        public void Jump(int meters)\n" +
                "        {\n" +
                "            if (meters < 0)\n" +
                "                // New nameof() expression; compiler will check that the identifier exists\n" +
                "                // nameof(x) == \"x\"\n" +
                "                // Prevents e.g. parameter names changing but not updated in error messages\n" +
                "                throw new ArgumentException(\"Cannot jump negative amount!\", nameof(meters));\n" +
                "\n" +
                "            Damage += meters;\n" +
                "        }\n" +
                "\n" +
                "        // Expression-bodied properties ...\n" +
                "        public bool Broken\n" +
                "            => Damage > 100;\n" +
                "\n" +
                "        // ... and methods\n" +
                "        public override string ToString()\n" +
                "            // Interpolated string\n" +
                "            => $\"{Name}. Damage taken: {Damage}\";\n" +
                "\n" +
                "        public string SummonGenie()\n" +
                "            // Null-conditional operators\n" +
                "            // x?.y will return null immediately if x is null; y is not evaluated\n" +
                "            => GenieName?.ToUpper();\n" +
                "    }\n" +
                "\n" +
                "    static class MagicService\n" +
                "    {\n" +
                "        private static bool LogException(Exception ex)\n" +
                "        {\n" +
                "            // log exception somewhere\n" +
                "            return false;\n" +
                "        }\n" +
                "\n" +
                "        public static bool CastSpell(string spell)\n" +
                "        {\n" +
                "            try\n" +
                "            {\n" +
                "                // Pretend we call API here\n" +
                "                throw new MagicServiceException(\"Spell failed\", 42);\n" +
                "\n" +
                "                // Spell succeeded\n" +
                "                return true;\n" +
                "            }\n" +
                "            // Only catch if Code is 42 i.e. spell failed\n" +
                "            catch(MagicServiceException ex) when (ex.Code == 42)\n" +
                "            {\n" +
                "                // Spell failed\n" +
                "                return false;\n" +
                "            }\n" +
                "            // Other exceptions, or MagicServiceException where Code is not 42\n" +
                "            catch(Exception ex) when (LogException(ex))\n" +
                "            {\n" +
                "                // Execution never reaches this block\n" +
                "                // The stack is not unwound\n" +
                "            }\n" +
                "            return false;\n" +
                "            // Note that catching a MagicServiceException and rethrowing if Code\n" +
                "            // is not 42 or 117 is different, as then the final catch-all block\n" +
                "            // will not catch the rethrown exception\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public class MagicServiceException : Exception\n" +
                "    {\n" +
                "        public int Code { get; }\n" +
                "\n" +
                "        public MagicServiceException(string message, int code) : base(message)\n" +
                "        {\n" +
                "            Code = code;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public static class PragmaWarning {\n" +
                "        // Obsolete attribute\n" +
                "        [Obsolete(\"Use NewMethod instead\", false)]\n" +
                "        public static void ObsoleteMethod()\n" +
                "        {\n" +
                "            // obsolete code\n" +
                "        }\n" +
                "\n" +
                "        public static void NewMethod()\n" +
                "        {\n" +
                "            // new code\n" +
                "        }\n" +
                "\n" +
                "        public static void Main()\n" +
                "        {\n" +
                "            ObsoleteMethod(); // CS0618: 'ObsoleteMethod is obsolete: Use NewMethod instead'\n" +
                "#pragma warning disable CS0618\n" +
                "            ObsoleteMethod(); // no warning\n" +
                "#pragma warning restore CS0618\n" +
                "            ObsoleteMethod(); // CS0618: 'ObsoleteMethod is obsolete: Use NewMethod instead'\n" +
                "        }\n" +
                "    }\n" +
                "} // End Namespace\n" +
                "\n" +
                "using System;\n" +
                "// C# 6, static using\n" +
                "using static System.Math;\n" +
                "\n" +
                "namespace Learning.More.CSharp\n" +
                "{\n" +
                "    class StaticUsing\n" +
                "    {\n" +
                "        static void Main()\n" +
                "        {\n" +
                "            // Without a static using statement..\n" +
                "            Console.WriteLine(\"The square root of 4 is {}.\", Math.Sqrt(4));\n" +
                "            // With one\n" +
                "            Console.WriteLine(\"The square root of 4 is {}.\", Sqrt(4));\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "// New C# 7 Feature\n" +
                "// Install Microsoft.Net.Compilers Latest from Nuget\n" +
                "// Install System.ValueTuple Latest from Nuget\n" +
                "using System;\n" +
                "namespace Csharp7\n" +
                "{\n" +
                "    // TUPLES, DECONSTRUCTION AND DISCARDS\n" +
                "    class TuplesTest\n" +
                "    {\n" +
                "        public (string, string) GetName()\n" +
                "        {\n" +
                "            // Fields in tuples are by default named Item1, Item2...\n" +
                "            var names1 = (\"Peter\", \"Parker\");\n" +
                "            Console.WriteLine(names1.Item2);  // => Parker\n" +
                "\n" +
                "            // Fields can instead be explicitly named\n" +
                "            // Type 1 Declaration\n" +
                "            (string FirstName, string LastName) names2 = (\"Peter\", \"Parker\");\n" +
                "\n" +
                "            // Type 2 Declaration\n" +
                "            var names3 = (First:\"Peter\", Last:\"Parker\");\n" +
                "\n" +
                "            Console.WriteLine(names2.FirstName);  // => Peter\n" +
                "            Console.WriteLine(names3.Last);  // => Parker\n" +
                "\n" +
                "            return names3;\n" +
                "        }\n" +
                "\n" +
                "        public string GetLastName() {\n" +
                "            var fullName = GetName();\n" +
                "\n" +
                "            // Tuples can be deconstructed\n" +
                "            (string firstName, string lastName) = fullName;\n" +
                "\n" +
                "            // Fields in a deconstructed tuple can be discarded by using _\n" +
                "            var (_, last) = fullName;\n" +
                "            return last;\n" +
                "        }\n" +
                "\n" +
                "        // Any type can be deconstructed in the same way by\n" +
                "        // specifying a Deconstruct method\n" +
                "        public int randomNumber = 4;\n" +
                "        public int anotherRandomNumber = 10;\n" +
                "\n" +
                "        public void Deconstruct(out int randomNumber, out int anotherRandomNumber)\n" +
                "        {\n" +
                "            randomNumber = this.randomNumber;\n" +
                "            anotherRandomNumber = this.anotherRandomNumber;\n" +
                "        }\n" +
                "\n" +
                "        static void Main(string[] args)\n" +
                "        {\n" +
                "            var tt = new TuplesTest();\n" +
                "            (int num1, int num2) = tt;\n" +
                "            Console.WriteLine($\"num1: {num1}, num2: {num2}\");  // => num1: 4, num2: 10\n" +
                "\n" +
                "            Console.WriteLine(tt.GetLastName());\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    // PATTERN MATCHING\n" +
                "    class PatternMatchingTest\n" +
                "    {\n" +
                "        public static (string, int)? CreateLogMessage(object data)\n" +
                "        {\n" +
                "            switch(data)\n" +
                "            {\n" +
                "                // Additional filtering using when\n" +
                "                case System.Net.Http.HttpRequestException h when h.Message.Contains(\"404\"):\n" +
                "                    return (h.Message, 404);\n" +
                "                case System.Net.Http.HttpRequestException h when h.Message.Contains(\"400\"):\n" +
                "                    return (h.Message, 400);\n" +
                "                case Exception e:\n" +
                "                    return (e.Message, 500);\n" +
                "                case string s:\n" +
                "                    return (s, s.Contains(\"Error\") ? 500 : 200);\n" +
                "                case null:\n" +
                "                    return null;\n" +
                "                default:\n" +
                "                    return (data.ToString(), 500);\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    // REFERENCE LOCALS\n" +
                "    // Allow you to return a reference to an object instead of just its value\n" +
                "    class RefLocalsTest\n" +
                "    {\n" +
                "        // note ref in return\n" +
                "        public static ref string FindItem(string[] arr, string el)\n" +
                "        {\n" +
                "            for(int i=0; i<arr.Length; i++)\n" +
                "            {\n" +
                "                if(arr[i] == el) {\n" +
                "                    // return the reference\n" +
                "                    return ref arr[i];\n" +
                "                }\n" +
                "            }\n" +
                "            throw new Exception(\"Item not found\");\n" +
                "        }\n" +
                "\n" +
                "        public static void SomeMethod()\n" +
                "        {\n" +
                "            string[] arr = {\"this\", \"is\", \"an\", \"array\"};\n" +
                "\n" +
                "            // note refs everywhere\n" +
                "            ref string item = ref FindItem(arr, \"array\");\n" +
                "            item = \"apple\";\n" +
                "            Console.WriteLine(arr[3]);  // => apple\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    // LOCAL FUNCTIONS\n" +
                "    class LocalFunctionTest\n" +
                "    {\n" +
                "        private static int _id = 0;\n" +
                "        public int id;\n" +
                "        public LocalFunctionTest()\n" +
                "        {\n" +
                "            id = generateId();\n" +
                "\n" +
                "            // This local function can only be accessed in this scope\n" +
                "            int generateId()\n" +
                "            {\n" +
                "                return _id++;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        public static void AnotherMethod()\n" +
                "        {\n" +
                "            var lf1 = new LocalFunctionTest();\n" +
                "            var lf2 = new LocalFunctionTest();\n" +
                "            Console.WriteLine($\"{lf1.id}, {lf2.id}\");  // => 0, 1\n" +
                "\n" +
                "            int id = generateId();\n" +
                "            // error CS0103: The name 'generateId' does not exist in the current context\n" +
                "        }\n" +
                "    }\n" +
                "}";
        database = FirebaseDatabase.getInstance("https://tynkr-3915c-default-rtdb.asia-southeast1.firebasedatabase.app/");
        binding.codeView.setOnHighlightListener(this)
                .setOnHighlightListener(this)
                .setTheme(Theme.MONOKAI_SUBLIME)
                .setCode(code)
                .setLanguage(Language.C_SHARP)
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
