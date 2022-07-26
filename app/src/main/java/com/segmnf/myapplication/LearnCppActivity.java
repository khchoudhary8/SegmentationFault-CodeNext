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
import com.segmnf.myapplication.databinding.ActivityLearnCppBinding;

import br.tiagohm.codeview.CodeView;
import br.tiagohm.codeview.Language;
import br.tiagohm.codeview.Theme;

public class LearnCppActivity extends AppCompatActivity implements CodeView.OnHighlightListener {
    ActivityLearnCppBinding binding;
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
        binding = ActivityLearnCppBinding.inflate(getLayoutInflater());
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
        String code= "//////////////////\n" +
                "// Comparison to C\n" +
                "//////////////////\n" +
                "\n" +
                "// C++ is _almost_ a superset of C and shares its basic syntax for\n" +
                "// variable declarations, primitive types, and functions.\n" +
                "\n" +
                "// Just like in C, your program's entry point is a function called\n" +
                "// main with an integer return type.\n" +
                "// This value serves as the program's exit status.\n" +
                "// See https://en.wikipedia.org/wiki/Exit_status for more information.\n" +
                "int main(int argc, char** argv)\n" +
                "{\n" +
                "    // Command line arguments are passed in by argc and argv in the same way\n" +
                "    // they are in C.\n" +
                "    // argc indicates the number of arguments,\n" +
                "    // and argv is an array of C-style strings (char*)\n" +
                "    // representing the arguments.\n" +
                "    // The first argument is the name by which the program was called.\n" +
                "    // argc and argv can be omitted if you do not care about arguments,\n" +
                "    // giving the function signature of int main()\n" +
                "\n" +
                "    // An exit status of 0 indicates success.\n" +
                "    return 0;\n" +
                "}\n" +
                "\n" +
                "// However, C++ varies in some of the following ways:\n" +
                "\n" +
                "// In C++, character literals are chars\n" +
                "sizeof('c') == sizeof(char) == 1\n" +
                "\n" +
                "// In C, character literals are ints\n" +
                "sizeof('c') == sizeof(int)\n" +
                "\n" +
                "\n" +
                "// C++ has strict prototyping\n" +
                "void func(); // function which accepts no arguments\n" +
                "\n" +
                "// In C\n" +
                "void func(); // function which may accept any number of arguments\n" +
                "\n" +
                "// Use nullptr instead of NULL in C++\n" +
                "int* ip = nullptr;\n" +
                "\n" +
                "// C standard headers are available in C++.\n" +
                "// C headers end in .h, while\n" +
                "// C++ headers are prefixed with \"c\" and have no \".h\" suffix.\n" +
                "\n" +
                "// The C++ standard version:\n" +
                "#include <cstdio>\n" +
                "\n" +
                "// The C standard version:\n" +
                "#include <stdio.h>\n" +
                "\n" +
                "int main()\n" +
                "{\n" +
                "    printf(\"Hello, world!\\n\");\n" +
                "    return 0;\n" +
                "}\n" +
                "\n" +
                "///////////////////////\n" +
                "// Function overloading\n" +
                "///////////////////////\n" +
                "\n" +
                "// C++ supports function overloading\n" +
                "// provided each function takes different parameters.\n" +
                "\n" +
                "void print(char const* myString)\n" +
                "{\n" +
                "    printf(\"String %s\\n\", myString);\n" +
                "}\n" +
                "\n" +
                "void print(int myInt)\n" +
                "{\n" +
                "    printf(\"My int is %d\", myInt);\n" +
                "}\n" +
                "\n" +
                "int main()\n" +
                "{\n" +
                "    print(\"Hello\"); // Resolves to void print(const char*)\n" +
                "    print(15); // Resolves to void print(int)\n" +
                "}\n" +
                "\n" +
                "/////////////////////////////\n" +
                "// Default function arguments\n" +
                "/////////////////////////////\n" +
                "\n" +
                "// You can provide default arguments for a function\n" +
                "// if they are not provided by the caller.\n" +
                "\n" +
                "void doSomethingWithInts(int a = 1, int b = 4)\n" +
                "{\n" +
                "    // Do something with the ints here\n" +
                "}\n" +
                "\n" +
                "int main()\n" +
                "{\n" +
                "    doSomethingWithInts();      // a = 1,  b = 4\n" +
                "    doSomethingWithInts(20);    // a = 20, b = 4\n" +
                "    doSomethingWithInts(20, 5); // a = 20, b = 5\n" +
                "}\n" +
                "\n" +
                "// Default arguments must be at the end of the arguments list.\n" +
                "\n" +
                "void invalidDeclaration(int a = 1, int b) // Error!\n" +
                "{\n" +
                "}\n" +
                "\n" +
                "\n" +
                "/////////////\n" +
                "// Namespaces\n" +
                "/////////////\n" +
                "\n" +
                "// Namespaces provide separate scopes for variable, function,\n" +
                "// and other declarations.\n" +
                "// Namespaces can be nested.\n" +
                "\n" +
                "namespace First {\n" +
                "    namespace Nested {\n" +
                "        void foo()\n" +
                "        {\n" +
                "            printf(\"This is First::Nested::foo\\n\");\n" +
                "        }\n" +
                "    } // end namespace Nested\n" +
                "} // end namespace First\n" +
                "\n" +
                "namespace Second {\n" +
                "    void foo()\n" +
                "    {\n" +
                "        printf(\"This is Second::foo\\n\");\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "void foo()\n" +
                "{\n" +
                "    printf(\"This is global foo\\n\");\n" +
                "}\n" +
                "\n" +
                "int main()\n" +
                "{\n" +
                "    // Includes all symbols from namespace Second into the current scope. Note\n" +
                "    // that simply foo() no longer works, since it is now ambiguous whether\n" +
                "    // we're calling the foo in namespace Second or the top level.\n" +
                "    using namespace Second;\n" +
                "\n" +
                "    Second::foo(); // prints \"This is Second::foo\"\n" +
                "    First::Nested::foo(); // prints \"This is First::Nested::foo\"\n" +
                "    ::foo(); // prints \"This is global foo\"\n" +
                "}\n" +
                "\n" +
                "///////////////\n" +
                "// Input/Output\n" +
                "///////////////\n" +
                "\n" +
                "// C++ input and output uses streams\n" +
                "// cin, cout, and cerr represent stdin, stdout, and stderr.\n" +
                "// << is the insertion operator and >> is the extraction operator.\n" +
                "\n" +
                "#include <iostream> // Include for I/O streams\n" +
                "\n" +
                "using namespace std; // Streams are in the std namespace (standard library)\n" +
                "\n" +
                "int main()\n" +
                "{\n" +
                "   int myInt;\n" +
                "\n" +
                "   // Prints to stdout (or terminal/screen)\n" +
                "   cout << \"Enter your favorite number:\\n\";\n" +
                "   // Takes in input\n" +
                "   cin >> myInt;\n" +
                "\n" +
                "   // cout can also be formatted\n" +
                "   cout << \"Your favorite number is \" << myInt << '\\n';\n" +
                "   // prints \"Your favorite number is <myInt>\"\n" +
                "\n" +
                "   cerr << \"Used for error messages\";\n" +
                "}\n" +
                "\n" +
                "//////////\n" +
                "// Strings\n" +
                "//////////\n" +
                "\n" +
                "// Strings in C++ are objects and have many member functions\n" +
                "#include <string>\n" +
                "\n" +
                "using namespace std; // Strings are also in the namespace std (standard library)\n" +
                "\n" +
                "string myString = \"Hello\";\n" +
                "string myOtherString = \" World\";\n" +
                "\n" +
                "// + is used for concatenation.\n" +
                "cout << myString + myOtherString; // \"Hello World\"\n" +
                "\n" +
                "cout << myString + \" You\"; // \"Hello You\"\n" +
                "\n" +
                "// C++ strings are mutable.\n" +
                "myString.append(\" Dog\");\n" +
                "cout << myString; // \"Hello Dog\"\n" +
                "\n" +
                "\n" +
                "/////////////\n" +
                "// References\n" +
                "/////////////\n" +
                "\n" +
                "// In addition to pointers like the ones in C,\n" +
                "// C++ has _references_.\n" +
                "// These are pointer types that cannot be reassigned once set\n" +
                "// and cannot be null.\n" +
                "// They also have the same syntax as the variable itself:\n" +
                "// No * is needed for dereferencing and\n" +
                "// & (address of) is not used for assignment.\n" +
                "\n" +
                "using namespace std;\n" +
                "\n" +
                "string foo = \"I am foo\";\n" +
                "string bar = \"I am bar\";\n" +
                "\n" +
                "\n" +
                "string& fooRef = foo; // This creates a reference to foo.\n" +
                "fooRef += \". Hi!\"; // Modifies foo through the reference\n" +
                "cout << fooRef; // Prints \"I am foo. Hi!\"\n" +
                "\n" +
                "// Doesn't reassign \"fooRef\". This is the same as \"foo = bar\", and\n" +
                "//   foo == \"I am bar\"\n" +
                "// after this line.\n" +
                "cout << &fooRef << endl; //Prints the address of foo\n" +
                "fooRef = bar;\n" +
                "cout << &fooRef << endl; //Still prints the address of foo\n" +
                "cout << fooRef;  // Prints \"I am bar\"\n" +
                "\n" +
                "// The address of fooRef remains the same, i.e. it is still referring to foo.\n" +
                "\n" +
                "\n" +
                "const string& barRef = bar; // Create a const reference to bar.\n" +
                "// Like C, const values (and pointers and references) cannot be modified.\n" +
                "barRef += \". Hi!\"; // Error, const references cannot be modified.\n" +
                "\n" +
                "// Sidetrack: Before we talk more about references, we must introduce a concept\n" +
                "// called a temporary object. Suppose we have the following code:\n" +
                "string tempObjectFun() { ... }\n" +
                "string retVal = tempObjectFun();\n" +
                "\n" +
                "// What happens in the second line is actually:\n" +
                "//   - a string object is returned from tempObjectFun\n" +
                "//   - a new string is constructed with the returned object as argument to the\n" +
                "//     constructor\n" +
                "//   - the returned object is destroyed\n" +
                "// The returned object is called a temporary object. Temporary objects are\n" +
                "// created whenever a function returns an object, and they are destroyed at the\n" +
                "// end of the evaluation of the enclosing expression (Well, this is what the\n" +
                "// standard says, but compilers are allowed to change this behavior. Look up\n" +
                "// \"return value optimization\" if you're into this kind of details). So in this\n" +
                "// code:\n" +
                "foo(bar(tempObjectFun()))\n" +
                "\n" +
                "// assuming foo and bar exist, the object returned from tempObjectFun is\n" +
                "// passed to bar, and it is destroyed before foo is called.\n" +
                "\n" +
                "// Now back to references. The exception to the \"at the end of the enclosing\n" +
                "// expression\" rule is if a temporary object is bound to a const reference, in\n" +
                "// which case its life gets extended to the current scope:\n" +
                "\n" +
                "void constReferenceTempObjectFun() {\n" +
                "  // constRef gets the temporary object, and it is valid until the end of this\n" +
                "  // function.\n" +
                "  const string& constRef = tempObjectFun();\n" +
                "  ...\n" +
                "}\n" +
                "\n" +
                "// Another kind of reference introduced in C++11 is specifically for temporary\n" +
                "// objects. You cannot have a variable of its type, but it takes precedence in\n" +
                "// overload resolution:\n" +
                "\n" +
                "void someFun(string& s) { ... }  // Regular reference\n" +
                "void someFun(string&& s) { ... }  // Reference to temporary object\n" +
                "\n" +
                "string foo;\n" +
                "someFun(foo);  // Calls the version with regular reference\n" +
                "someFun(tempObjectFun());  // Calls the version with temporary reference\n" +
                "\n" +
                "// For example, you will see these two versions of constructors for\n" +
                "// std::basic_string:\n" +
                "basic_string(const basic_string& other);\n" +
                "basic_string(basic_string&& other);\n" +
                "\n" +
                "// Idea being if we are constructing a new string from a temporary object (which\n" +
                "// is going to be destroyed soon anyway), we can have a more efficient\n" +
                "// constructor that \"salvages\" parts of that temporary string. You will see this\n" +
                "// concept referred to as \"move semantics\".\n" +
                "\n" +
                "/////////////////////\n" +
                "// Enums\n" +
                "/////////////////////\n" +
                "\n" +
                "// Enums are a way to assign a value to a constant most commonly used for\n" +
                "// easier visualization and reading of code\n" +
                "enum ECarTypes\n" +
                "{\n" +
                "  Sedan,\n" +
                "  Hatchback,\n" +
                "  SUV,\n" +
                "  Wagon\n" +
                "};\n" +
                "\n" +
                "ECarTypes GetPreferredCarType()\n" +
                "{\n" +
                "    return ECarTypes::Hatchback;\n" +
                "}\n" +
                "\n" +
                "// As of C++11 there is an easy way to assign a type to the enum which can be\n" +
                "// useful in serialization of data and converting enums back-and-forth between\n" +
                "// the desired type and their respective constants\n" +
                "enum ECarTypes : uint8_t\n" +
                "{\n" +
                "  Sedan, // 0\n" +
                "  Hatchback, // 1\n" +
                "  SUV = 254, // 254\n" +
                "  Hybrid // 255\n" +
                "};\n" +
                "\n" +
                "void WriteByteToFile(uint8_t InputValue)\n" +
                "{\n" +
                "    // Serialize the InputValue to a file\n" +
                "}\n" +
                "\n" +
                "void WritePreferredCarTypeToFile(ECarTypes InputCarType)\n" +
                "{\n" +
                "    // The enum is implicitly converted to a uint8_t due to its declared enum type\n" +
                "    WriteByteToFile(InputCarType);\n" +
                "}\n" +
                "\n" +
                "// On the other hand you may not want enums to be accidentally cast to an integer\n" +
                "// type or to other enums so it is instead possible to create an enum class which\n" +
                "// won't be implicitly converted\n" +
                "enum class ECarTypes : uint8_t\n" +
                "{\n" +
                "  Sedan, // 0\n" +
                "  Hatchback, // 1\n" +
                "  SUV = 254, // 254\n" +
                "  Hybrid // 255\n" +
                "};\n" +
                "\n" +
                "void WriteByteToFile(uint8_t InputValue)\n" +
                "{\n" +
                "    // Serialize the InputValue to a file\n" +
                "}\n" +
                "\n" +
                "void WritePreferredCarTypeToFile(ECarTypes InputCarType)\n" +
                "{\n" +
                "    // Won't compile even though ECarTypes is a uint8_t due to the enum\n" +
                "    // being declared as an \"enum class\"!\n" +
                "    WriteByteToFile(InputCarType);\n" +
                "}\n" +
                "\n" +
                "//////////////////////////////////////////\n" +
                "// Classes and object-oriented programming\n" +
                "//////////////////////////////////////////\n" +
                "\n" +
                "// First example of classes\n" +
                "#include <iostream>\n" +
                "\n" +
                "// Declare a class.\n" +
                "// Classes are usually declared in header (.h or .hpp) files.\n" +
                "class Dog {\n" +
                "    // Member variables and functions are private by default.\n" +
                "    std::string name;\n" +
                "    int weight;\n" +
                "\n" +
                "// All members following this are public\n" +
                "// until \"private:\" or \"protected:\" is found.\n" +
                "public:\n" +
                "\n" +
                "    // Default constructor\n" +
                "    Dog();\n" +
                "\n" +
                "    // Member function declarations (implementations to follow)\n" +
                "    // Note that we use std::string here instead of placing\n" +
                "    // using namespace std;\n" +
                "    // above.\n" +
                "    // Never put a \"using namespace\" statement in a header.\n" +
                "    void setName(const std::string& dogsName);\n" +
                "\n" +
                "    void setWeight(int dogsWeight);\n" +
                "\n" +
                "    // Functions that do not modify the state of the object\n" +
                "    // should be marked as const.\n" +
                "    // This allows you to call them if given a const reference to the object.\n" +
                "    // Also note the functions must be explicitly declared as _virtual_\n" +
                "    // in order to be overridden in derived classes.\n" +
                "    // Functions are not virtual by default for performance reasons.\n" +
                "    virtual void print() const;\n" +
                "\n" +
                "    // Functions can also be defined inside the class body.\n" +
                "    // Functions defined as such are automatically inlined.\n" +
                "    void bark() const { std::cout << name << \" barks!\\n\"; }\n" +
                "\n" +
                "    // Along with constructors, C++ provides destructors.\n" +
                "    // These are called when an object is deleted or falls out of scope.\n" +
                "    // This enables powerful paradigms such as RAII\n" +
                "    // (see below)\n" +
                "    // The destructor should be virtual if a class is to be derived from;\n" +
                "    // if it is not virtual, then the derived class' destructor will\n" +
                "    // not be called if the object is destroyed through a base-class reference\n" +
                "    // or pointer.\n" +
                "    virtual ~Dog();\n" +
                "\n" +
                "}; // A semicolon must follow the class definition.\n" +
                "\n" +
                "// Class member functions are usually implemented in .cpp files.\n" +
                "Dog::Dog()\n" +
                "{\n" +
                "    std::cout << \"A dog has been constructed\\n\";\n" +
                "}\n" +
                "\n" +
                "// Objects (such as strings) should be passed by reference\n" +
                "// if you are modifying them or const reference if you are not.\n" +
                "void Dog::setName(const std::string& dogsName)\n" +
                "{\n" +
                "    name = dogsName;\n" +
                "}\n" +
                "\n" +
                "void Dog::setWeight(int dogsWeight)\n" +
                "{\n" +
                "    weight = dogsWeight;\n" +
                "}\n" +
                "\n" +
                "// Notice that \"virtual\" is only needed in the declaration, not the definition.\n" +
                "void Dog::print() const\n" +
                "{\n" +
                "    std::cout << \"Dog is \" << name << \" and weighs \" << weight << \"kg\\n\";\n" +
                "}\n" +
                "\n" +
                "Dog::~Dog()\n" +
                "{\n" +
                "    std::cout << \"Goodbye \" << name << '\\n';\n" +
                "}\n" +
                "\n" +
                "int main() {\n" +
                "    Dog myDog; // prints \"A dog has been constructed\"\n" +
                "    myDog.setName(\"Barkley\");\n" +
                "    myDog.setWeight(10);\n" +
                "    myDog.print(); // prints \"Dog is Barkley and weighs 10 kg\"\n" +
                "    return 0;\n" +
                "} // prints \"Goodbye Barkley\"\n" +
                "\n" +
                "// Inheritance:\n" +
                "\n" +
                "// This class inherits everything public and protected from the Dog class\n" +
                "// as well as private but may not directly access private members/methods\n" +
                "// without a public or protected method for doing so\n" +
                "class OwnedDog : public Dog {\n" +
                "\n" +
                "public:\n" +
                "    void setOwner(const std::string& dogsOwner);\n" +
                "\n" +
                "    // Override the behavior of the print function for all OwnedDogs. See\n" +
                "    // https://en.wikipedia.org/wiki/Polymorphism_(computer_science)#Subtyping\n" +
                "    // for a more general introduction if you are unfamiliar with\n" +
                "    // subtype polymorphism.\n" +
                "    // The override keyword is optional but makes sure you are actually\n" +
                "    // overriding the method in a base class.\n" +
                "    void print() const override;\n" +
                "\n" +
                "private:\n" +
                "    std::string owner;\n" +
                "};\n" +
                "\n" +
                "// Meanwhile, in the corresponding .cpp file:\n" +
                "\n" +
                "void OwnedDog::setOwner(const std::string& dogsOwner)\n" +
                "{\n" +
                "    owner = dogsOwner;\n" +
                "}\n" +
                "\n" +
                "void OwnedDog::print() const\n" +
                "{\n" +
                "    Dog::print(); // Call the print function in the base Dog class\n" +
                "    std::cout << \"Dog is owned by \" << owner << '\\n';\n" +
                "    // Prints \"Dog is <name> and weights <weight>\"\n" +
                "    //        \"Dog is owned by <owner>\"\n" +
                "}\n" +
                "\n" +
                "//////////////////////////////////////////\n" +
                "// Initialization and Operator Overloading\n" +
                "//////////////////////////////////////////\n" +
                "\n" +
                "// In C++ you can overload the behavior of operators such as +, -, *, /, etc.\n" +
                "// This is done by defining a function which is called\n" +
                "// whenever the operator is used.\n" +
                "\n" +
                "#include <iostream>\n" +
                "using namespace std;\n" +
                "\n" +
                "class Point {\n" +
                "public:\n" +
                "    // Member variables can be given default values in this manner.\n" +
                "    double x = 0;\n" +
                "    double y = 0;\n" +
                "\n" +
                "    // Define a default constructor which does nothing\n" +
                "    // but initialize the Point to the default value (0, 0)\n" +
                "    Point() { };\n" +
                "\n" +
                "    // The following syntax is known as an initialization list\n" +
                "    // and is the proper way to initialize class member values\n" +
                "    Point (double a, double b) :\n" +
                "        x(a),\n" +
                "        y(b)\n" +
                "    { /* Do nothing except initialize the values */ }\n" +
                "\n" +
                "    // Overload the + operator.\n" +
                "    Point operator+(const Point& rhs) const;\n" +
                "\n" +
                "    // Overload the += operator\n" +
                "    Point& operator+=(const Point& rhs);\n" +
                "\n" +
                "    // It would also make sense to add the - and -= operators,\n" +
                "    // but we will skip those for brevity.\n" +
                "};\n" +
                "\n" +
                "Point Point::operator+(const Point& rhs) const\n" +
                "{\n" +
                "    // Create a new point that is the sum of this one and rhs.\n" +
                "    return Point(x + rhs.x, y + rhs.y);\n" +
                "}\n" +
                "\n" +
                "// It's good practice to return a reference to the leftmost variable of\n" +
                "// an assignment. `(a += b) == c` will work this way.\n" +
                "Point& Point::operator+=(const Point& rhs)\n" +
                "{\n" +
                "    x += rhs.x;\n" +
                "    y += rhs.y;\n" +
                "\n" +
                "    // `this` is a pointer to the object, on which a method is called.\n" +
                "    return *this;\n" +
                "}\n" +
                "\n" +
                "int main () {\n" +
                "    Point up (0,1);\n" +
                "    Point right (1,0);\n" +
                "    // This calls the Point + operator\n" +
                "    // Point up calls the + (function) with right as its parameter\n" +
                "    Point result = up + right;\n" +
                "    // Prints \"Result is upright (1,1)\"\n" +
                "    cout << \"Result is upright (\" << result.x << ',' << result.y << \")\\n\";\n" +
                "    return 0;\n" +
                "}\n" +
                "\n" +
                "/////////////////////\n" +
                "// Templates\n" +
                "/////////////////////\n" +
                "\n" +
                "// Templates in C++ are mostly used for generic programming, though they are\n" +
                "// much more powerful than generic constructs in other languages. They also\n" +
                "// support explicit and partial specialization and functional-style type\n" +
                "// classes; in fact, they are a Turing-complete functional language embedded\n" +
                "// in C++!\n" +
                "\n" +
                "// We start with the kind of generic programming you might be familiar with. To\n" +
                "// define a class or function that takes a type parameter:\n" +
                "template<class T>\n" +
                "class Box {\n" +
                "public:\n" +
                "    // In this class, T can be used as any other type.\n" +
                "    void insert(const T&) { ... }\n" +
                "};\n" +
                "\n" +
                "// During compilation, the compiler actually generates copies of each template\n" +
                "// with parameters substituted, so the full definition of the class must be\n" +
                "// present at each invocation. This is why you will see template classes defined\n" +
                "// entirely in header files.\n" +
                "\n" +
                "// To instantiate a template class on the stack:\n" +
                "Box<int> intBox;\n" +
                "\n" +
                "// and you can use it as you would expect:\n" +
                "intBox.insert(123);\n" +
                "\n" +
                "// You can, of course, nest templates:\n" +
                "Box<Box<int> > boxOfBox;\n" +
                "boxOfBox.insert(intBox);\n" +
                "\n" +
                "// Until C++11, you had to place a space between the two '>'s, otherwise '>>'\n" +
                "// would be parsed as the right shift operator.\n" +
                "\n" +
                "// You will sometimes see\n" +
                "//   template<typename T>\n" +
                "// instead. The 'class' keyword and 'typename' keywords are _mostly_\n" +
                "// interchangeable in this case. For the full explanation, see\n" +
                "//   https://en.wikipedia.org/wiki/Typename\n" +
                "// (yes, that keyword has its own Wikipedia page).\n" +
                "\n" +
                "// Similarly, a template function:\n" +
                "template<class T>\n" +
                "void barkThreeTimes(const T& input)\n" +
                "{\n" +
                "    input.bark();\n" +
                "    input.bark();\n" +
                "    input.bark();\n" +
                "}\n" +
                "\n" +
                "// Notice that nothing is specified about the type parameters here. The compiler\n" +
                "// will generate and then type-check every invocation of the template, so the\n" +
                "// above function works with any type 'T' that has a const 'bark' method!\n" +
                "\n" +
                "Dog fluffy;\n" +
                "fluffy.setName(\"Fluffy\")\n" +
                "barkThreeTimes(fluffy); // Prints \"Fluffy barks\" three times.\n" +
                "\n" +
                "// Template parameters don't have to be classes:\n" +
                "template<int Y>\n" +
                "void printMessage() {\n" +
                "  cout << \"Learn C++ in \" << Y << \" minutes!\" << endl;\n" +
                "}\n" +
                "\n" +
                "// And you can explicitly specialize templates for more efficient code. Of\n" +
                "// course, most real-world uses of specialization are not as trivial as this.\n" +
                "// Note that you still need to declare the function (or class) as a template\n" +
                "// even if you explicitly specified all parameters.\n" +
                "template<>\n" +
                "void printMessage<10>() {\n" +
                "  cout << \"Learn C++ faster in only 10 minutes!\" << endl;\n" +
                "}\n" +
                "\n" +
                "printMessage<20>();  // Prints \"Learn C++ in 20 minutes!\"\n" +
                "printMessage<10>();  // Prints \"Learn C++ faster in only 10 minutes!\"\n" +
                "\n" +
                "\n" +
                "/////////////////////\n" +
                "// Exception Handling\n" +
                "/////////////////////\n" +
                "\n" +
                "// The standard library provides a few exception types\n" +
                "// (see https://en.cppreference.com/w/cpp/error/exception)\n" +
                "// but any type can be thrown as an exception\n" +
                "#include <exception>\n" +
                "#include <stdexcept>\n" +
                "\n" +
                "// All exceptions thrown inside the _try_ block can be caught by subsequent\n" +
                "// _catch_ handlers.\n" +
                "try {\n" +
                "    // Do not allocate exceptions on the heap using _new_.\n" +
                "    throw std::runtime_error(\"A problem occurred\");\n" +
                "}\n" +
                "\n" +
                "// Catch exceptions by const reference if they are objects\n" +
                "catch (const std::exception& ex)\n" +
                "{\n" +
                "    std::cout << ex.what();\n" +
                "}\n" +
                "\n" +
                "// Catches any exception not caught by previous _catch_ blocks\n" +
                "catch (...)\n" +
                "{\n" +
                "    std::cout << \"Unknown exception caught\";\n" +
                "    throw; // Re-throws the exception\n" +
                "}\n" +
                "\n" +
                "///////\n" +
                "// RAII\n" +
                "///////\n" +
                "\n" +
                "// RAII stands for \"Resource Acquisition Is Initialization\".\n" +
                "// It is often considered the most powerful paradigm in C++\n" +
                "// and is the simple concept that a constructor for an object\n" +
                "// acquires that object's resources and the destructor releases them.\n" +
                "\n" +
                "// To understand how this is useful,\n" +
                "// consider a function that uses a C file handle:\n" +
                "void doSomethingWithAFile(const char* filename)\n" +
                "{\n" +
                "    // To begin with, assume nothing can fail.\n" +
                "\n" +
                "    FILE* fh = fopen(filename, \"r\"); // Open the file in read mode.\n" +
                "\n" +
                "    doSomethingWithTheFile(fh);\n" +
                "    doSomethingElseWithIt(fh);\n" +
                "\n" +
                "    fclose(fh); // Close the file handle.\n" +
                "}\n" +
                "\n" +
                "// Unfortunately, things are quickly complicated by error handling.\n" +
                "// Suppose fopen can fail, and that doSomethingWithTheFile and\n" +
                "// doSomethingElseWithIt return error codes if they fail.\n" +
                "//  (Exceptions are the preferred way of handling failure,\n" +
                "//   but some programmers, especially those with a C background,\n" +
                "//   disagree on the utility of exceptions).\n" +
                "// We now have to check each call for failure and close the file handle\n" +
                "// if a problem occurred.\n" +
                "bool doSomethingWithAFile(const char* filename)\n" +
                "{\n" +
                "    FILE* fh = fopen(filename, \"r\"); // Open the file in read mode\n" +
                "    if (fh == nullptr) // The returned pointer is null on failure.\n" +
                "        return false; // Report that failure to the caller.\n" +
                "\n" +
                "    // Assume each function returns false if it failed\n" +
                "    if (!doSomethingWithTheFile(fh)) {\n" +
                "        fclose(fh); // Close the file handle so it doesn't leak.\n" +
                "        return false; // Propagate the error.\n" +
                "    }\n" +
                "    if (!doSomethingElseWithIt(fh)) {\n" +
                "        fclose(fh); // Close the file handle so it doesn't leak.\n" +
                "        return false; // Propagate the error.\n" +
                "    }\n" +
                "\n" +
                "    fclose(fh); // Close the file handle so it doesn't leak.\n" +
                "    return true; // Indicate success\n" +
                "}\n" +
                "\n" +
                "// C programmers often clean this up a little bit using goto:\n" +
                "bool doSomethingWithAFile(const char* filename)\n" +
                "{\n" +
                "    FILE* fh = fopen(filename, \"r\");\n" +
                "    if (fh == nullptr)\n" +
                "        return false;\n" +
                "\n" +
                "    if (!doSomethingWithTheFile(fh))\n" +
                "        goto failure;\n" +
                "\n" +
                "    if (!doSomethingElseWithIt(fh))\n" +
                "        goto failure;\n" +
                "\n" +
                "    fclose(fh); // Close the file\n" +
                "    return true; // Indicate success\n" +
                "\n" +
                "failure:\n" +
                "    fclose(fh);\n" +
                "    return false; // Propagate the error\n" +
                "}\n" +
                "\n" +
                "// If the functions indicate errors using exceptions,\n" +
                "// things are a little cleaner, but still sub-optimal.\n" +
                "void doSomethingWithAFile(const char* filename)\n" +
                "{\n" +
                "    FILE* fh = fopen(filename, \"r\"); // Open the file in shared_ptrread mode\n" +
                "    if (fh == nullptr)\n" +
                "        throw std::runtime_error(\"Could not open the file.\");\n" +
                "\n" +
                "    try {\n" +
                "        doSomethingWithTheFile(fh);\n" +
                "        doSomethingElseWithIt(fh);\n" +
                "    }\n" +
                "    catch (...) {\n" +
                "        fclose(fh); // Be sure to close the file if an error occurs.\n" +
                "        throw; // Then re-throw the exception.\n" +
                "    }\n" +
                "\n" +
                "    fclose(fh); // Close the file\n" +
                "    // Everything succeeded\n" +
                "}\n" +
                "\n" +
                "// Compare this to the use of C++'s file stream class (fstream)\n" +
                "// fstream uses its destructor to close the file.\n" +
                "// Recall from above that destructors are automatically called\n" +
                "// whenever an object falls out of scope.\n" +
                "void doSomethingWithAFile(const std::string& filename)\n" +
                "{\n" +
                "    // ifstream is short for input file stream\n" +
                "    std::ifstream fh(filename); // Open the file\n" +
                "\n" +
                "    // Do things with the file\n" +
                "    doSomethingWithTheFile(fh);\n" +
                "    doSomethingElseWithIt(fh);\n" +
                "\n" +
                "} // The file is automatically closed here by the destructor\n" +
                "\n" +
                "// This has _massive_ advantages:\n" +
                "// 1. No matter what happens,\n" +
                "//    the resource (in this case the file handle) will be cleaned up.\n" +
                "//    Once you write the destructor correctly,\n" +
                "//    It is _impossible_ to forget to close the handle and leak the resource.\n" +
                "// 2. Note that the code is much cleaner.\n" +
                "//    The destructor handles closing the file behind the scenes\n" +
                "//    without you having to worry about it.\n" +
                "// 3. The code is exception safe.\n" +
                "//    An exception can be thrown anywhere in the function and cleanup\n" +
                "//    will still occur.\n" +
                "\n" +
                "// All idiomatic C++ code uses RAII extensively for all resources.\n" +
                "// Additional examples include\n" +
                "// - Memory using unique_ptr and shared_ptr\n" +
                "// - Containers - the standard library linked list,\n" +
                "//   vector (i.e. self-resizing array), hash maps, and so on\n" +
                "//   all automatically destroy their contents when they fall out of scope.\n" +
                "// - Mutexes using lock_guard and unique_lock\n" +
                "\n" +
                "\n" +
                "/////////////////////\n" +
                "// Smart Pointer\n" +
                "/////////////////////\n" +
                "\n" +
                "// Generally a smart pointer is a class which wraps a \"raw pointer\" (usage of \"new\"\n" +
                "// respectively malloc/calloc in C). The goal is to be able to\n" +
                "// manage the lifetime of the object being pointed to without ever needing to explicitly delete \n" +
                "// the object. The term itself simply describes a set of pointers with the\n" +
                "// mentioned abstraction.\n" +
                "// Smart pointers should preferred over raw pointers, to prevent\n" +
                "// risky memory leaks, which happen if you forget to delete an object.\n" +
                "\n" +
                "// Usage of a raw pointer:\n" +
                "Dog* ptr = new Dog();\n" +
                "ptr->bark();\n" +
                "delete ptr;\n" +
                "\n" +
                "// By using a smart pointer, you don't have to worry about the deletion\n" +
                "// of the object anymore.\n" +
                "// A smart pointer describes a policy, to count the references to the\n" +
                "// pointer. The object gets destroyed when the last\n" +
                "// reference to the object gets destroyed.\n" +
                "\n" +
                "// Usage of \"std::shared_ptr\":\n" +
                "void foo()\n" +
                "{\n" +
                "// It's no longer necessary to delete the Dog.\n" +
                "std::shared_ptr<Dog> doggo(new Dog());\n" +
                "doggo->bark();\n" +
                "}\n" +
                "\n" +
                "// Beware of possible circular references!!!\n" +
                "// There will be always a reference, so it will be never destroyed!\n" +
                "std::shared_ptr<Dog> doggo_one(new Dog());\n" +
                "std::shared_ptr<Dog> doggo_two(new Dog());\n" +
                "doggo_one = doggo_two; // p1 references p2\n" +
                "doggo_two = doggo_one; // p2 references p1\n" +
                "\n" +
                "// There are several kinds of smart pointers. \n" +
                "// The way you have to use them is always the same.\n" +
                "// This leads us to the question: when should we use each kind of smart pointer?\n" +
                "// std::unique_ptr - use it when you just want to hold one reference to\n" +
                "// the object.\n" +
                "// std::shared_ptr - use it when you want to hold multiple references to the\n" +
                "// same object and want to make sure that it's deallocated\n" +
                "// when all references are gone.\n" +
                "// std::weak_ptr - use it when you want to access\n" +
                "// the underlying object of a std::shared_ptr without causing that object to stay allocated.\n" +
                "// Weak pointers are used to prevent circular referencing.\n" +
                "\n" +
                "\n" +
                "/////////////////////\n" +
                "// Containers\n" +
                "/////////////////////\n" +
                "\n" +
                "// Containers or the Standard Template Library are some predefined templates.\n" +
                "// They manage the storage space for its elements and provide\n" +
                "// member functions to access and manipulate them.\n" +
                "\n" +
                "// Few containers are as follows:\n" +
                "\n" +
                "// Vector (Dynamic array)\n" +
                "// Allow us to Define the Array or list of objects at run time\n" +
                "#include <vector>\n" +
                "string val;\n" +
                "vector<string> my_vector; // initialize the vector\n" +
                "cin >> val;\n" +
                "my_vector.push_back(val); // will push the value of 'val' into vector (\"array\") my_vector\n" +
                "my_vector.push_back(val); // will push the value into the vector again (now having two elements)\n" +
                "\n" +
                "// To iterate through a vector we have 2 choices:\n" +
                "// Either classic looping (iterating through the vector from index 0 to its last index):\n" +
                "for (int i = 0; i < my_vector.size(); i++) {\n" +
                "    cout << my_vector[i] << endl; // for accessing a vector's element we can use the operator []\n" +
                "}\n" +
                "\n" +
                "// or using an iterator:\n" +
                "vector<string>::iterator it; // initialize the iterator for vector\n" +
                "for (it = my_vector.begin(); it != my_vector.end(); ++it) {\n" +
                "    cout << *it  << endl;\n" +
                "}\n" +
                "\n" +
                "// Set\n" +
                "// Sets are containers that store unique elements following a specific order.\n" +
                "// Set is a very useful container to store unique values in sorted order\n" +
                "// without any other functions or code.\n" +
                "\n" +
                "#include<set>\n" +
                "set<int> ST;    // Will initialize the set of int data type\n" +
                "ST.insert(30);  // Will insert the value 30 in set ST\n" +
                "ST.insert(10);  // Will insert the value 10 in set ST\n" +
                "ST.insert(20);  // Will insert the value 20 in set ST\n" +
                "ST.insert(30);  // Will insert the value 30 in set ST\n" +
                "// Now elements of sets are as follows\n" +
                "//  10 20 30\n" +
                "\n" +
                "// To erase an element\n" +
                "ST.erase(20);  // Will erase element with value 20\n" +
                "// Set ST: 10 30\n" +
                "// To iterate through Set we use iterators\n" +
                "set<int>::iterator it;\n" +
                "for(it=ST.begin();it!=ST.end();it++) {\n" +
                "    cout << *it << endl;\n" +
                "}\n" +
                "// Output:\n" +
                "// 10\n" +
                "// 30\n" +
                "\n" +
                "// To clear the complete container we use Container_name.clear()\n" +
                "ST.clear();\n" +
                "cout << ST.size();  // will print the size of set ST\n" +
                "// Output: 0\n" +
                "\n" +
                "// NOTE: for duplicate elements we can use multiset\n" +
                "// NOTE: For hash sets, use unordered_set. They are more efficient but\n" +
                "// do not preserve order. unordered_set is available since C++11\n" +
                "\n" +
                "// Map\n" +
                "// Maps store elements formed by a combination of a key value\n" +
                "// and a mapped value, following a specific order.\n" +
                "\n" +
                "#include<map>\n" +
                "map<char, int> mymap;  // Will initialize the map with key as char and value as int\n" +
                "\n" +
                "mymap.insert(pair<char,int>('A',1));\n" +
                "// Will insert value 1 for key A\n" +
                "mymap.insert(pair<char,int>('Z',26));\n" +
                "// Will insert value 26 for key Z\n" +
                "\n" +
                "// To iterate\n" +
                "map<char,int>::iterator it;\n" +
                "for (it=mymap.begin(); it!=mymap.end(); ++it)\n" +
                "    std::cout << it->first << \"->\" << it->second << std::endl;\n" +
                "// Output:\n" +
                "// A->1\n" +
                "// Z->26\n" +
                "\n" +
                "// To find the value corresponding to a key\n" +
                "it = mymap.find('Z');\n" +
                "cout << it->second;\n" +
                "\n" +
                "// Output: 26\n" +
                "\n" +
                "// NOTE: For hash maps, use unordered_map. They are more efficient but do\n" +
                "// not preserve order. unordered_map is available since C++11.\n" +
                "\n" +
                "// Containers with object keys of non-primitive values (custom classes) require\n" +
                "// compare function in the object itself or as a function pointer. Primitives\n" +
                "// have default comparators, but you can override it.\n" +
                "class Foo {\n" +
                "public:\n" +
                "    int j;\n" +
                "    Foo(int a) : j(a) {}\n" +
                "};\n" +
                "struct compareFunction {\n" +
                "    bool operator()(const Foo& a, const Foo& b) const {\n" +
                "        return a.j < b.j;\n" +
                "    }\n" +
                "};\n" +
                "// this isn't allowed (although it can vary depending on compiler)\n" +
                "// std::map<Foo, int> fooMap;\n" +
                "std::map<Foo, int, compareFunction> fooMap;\n" +
                "fooMap[Foo(1)]  = 1;\n" +
                "fooMap.find(Foo(1)); //true\n" +
                "\n" +
                "\n" +
                "///////////////////////////////////////\n" +
                "// Lambda Expressions (C++11 and above)\n" +
                "///////////////////////////////////////\n" +
                "\n" +
                "// lambdas are a convenient way of defining an anonymous function\n" +
                "// object right at the location where it is invoked or passed as\n" +
                "// an argument to a function.\n" +
                "\n" +
                "// For example, consider sorting a vector of pairs using the second\n" +
                "// value of the pair\n" +
                "\n" +
                "vector<pair<int, int> > tester;\n" +
                "tester.push_back(make_pair(3, 6));\n" +
                "tester.push_back(make_pair(1, 9));\n" +
                "tester.push_back(make_pair(5, 0));\n" +
                "\n" +
                "// Pass a lambda expression as third argument to the sort function\n" +
                "// sort is from the <algorithm> header\n" +
                "\n" +
                "sort(tester.begin(), tester.end(), [](const pair<int, int>& lhs, const pair<int, int>& rhs) {\n" +
                "        return lhs.second < rhs.second;\n" +
                "    });\n" +
                "\n" +
                "// Notice the syntax of the lambda expression,\n" +
                "// [] in the lambda is used to \"capture\" variables\n" +
                "// The \"Capture List\" defines what from the outside of the lambda should be available inside the function body and how.\n" +
                "// It can be either:\n" +
                "//     1. a value : [x]\n" +
                "//     2. a reference : [&x]\n" +
                "//     3. any variable currently in scope by reference [&]\n" +
                "//     4. same as 3, but by value [=]\n" +
                "// Example:\n" +
                "\n" +
                "vector<int> dog_ids;\n" +
                "// number_of_dogs = 3;\n" +
                "for(int i = 0; i < 3; i++) {\n" +
                "    dog_ids.push_back(i);\n" +
                "}\n" +
                "\n" +
                "int weight[3] = {30, 50, 10};\n" +
                "\n" +
                "// Say you want to sort dog_ids according to the dogs' weights\n" +
                "// So dog_ids should in the end become: [2, 0, 1]\n" +
                "\n" +
                "// Here's where lambda expressions come in handy\n" +
                "\n" +
                "sort(dog_ids.begin(), dog_ids.end(), [&weight](const int &lhs, const int &rhs) {\n" +
                "        return weight[lhs] < weight[rhs];\n" +
                "    });\n" +
                "// Note we captured \"weight\" by reference in the above example.\n" +
                "// More on Lambdas in C++ : https://stackoverflow.com/questions/7627098/what-is-a-lambda-expression-in-c11\n" +
                "\n" +
                "///////////////////////////////\n" +
                "// Range For (C++11 and above)\n" +
                "///////////////////////////////\n" +
                "\n" +
                "// You can use a range for loop to iterate over a container\n" +
                "int arr[] = {1, 10, 3};\n" +
                "\n" +
                "for(int elem: arr){\n" +
                "    cout << elem << endl;\n" +
                "}\n" +
                "\n" +
                "// You can use \"auto\" and not worry about the type of the elements of the container\n" +
                "// For example:\n" +
                "\n" +
                "for(auto elem: arr) {\n" +
                "    // Do something with each element of arr\n" +
                "}\n" +
                "\n" +
                "/////////////////////\n" +
                "// Fun stuff\n" +
                "/////////////////////\n" +
                "\n" +
                "// Aspects of C++ that may be surprising to newcomers (and even some veterans).\n" +
                "// This section is, unfortunately, wildly incomplete; C++ is one of the easiest\n" +
                "// languages with which to shoot yourself in the foot.\n" +
                "\n" +
                "// You can override private methods!\n" +
                "class Foo {\n" +
                "  virtual void bar();\n" +
                "};\n" +
                "class FooSub : public Foo {\n" +
                "  virtual void bar();  // Overrides Foo::bar!\n" +
                "};\n" +
                "\n" +
                "\n" +
                "// 0 == false == NULL (most of the time)!\n" +
                "bool* pt = new bool;\n" +
                "*pt = 0; // Sets the value points by 'pt' to false.\n" +
                "pt = 0;  // Sets 'pt' to the null pointer. Both lines compile without warnings.\n" +
                "\n" +
                "// nullptr is supposed to fix some of that issue:\n" +
                "int* pt2 = new int;\n" +
                "*pt2 = nullptr; // Doesn't compile\n" +
                "pt2 = nullptr;  // Sets pt2 to null.\n" +
                "\n" +
                "// There is an exception made for bools.\n" +
                "// This is to allow you to test for null pointers with if(!ptr),\n" +
                "// but as a consequence you can assign nullptr to a bool directly!\n" +
                "*pt = nullptr;  // This still compiles, even though '*pt' is a bool!\n" +
                "\n" +
                "\n" +
                "// '=' != '=' != '='!\n" +
                "// Calls Foo::Foo(const Foo&) or some variant (see move semantics) copy\n" +
                "// constructor.\n" +
                "Foo f2;\n" +
                "Foo f1 = f2;\n" +
                "\n" +
                "// Calls Foo::Foo(const Foo&) or variant, but only copies the 'Foo' part of\n" +
                "// 'fooSub'. Any extra members of 'fooSub' are discarded. This sometimes\n" +
                "// horrifying behavior is called \"object slicing.\"\n" +
                "FooSub fooSub;\n" +
                "Foo f1 = fooSub;\n" +
                "\n" +
                "// Calls Foo::operator=(Foo&) or variant.\n" +
                "Foo f1;\n" +
                "f1 = f2;\n" +
                "\n" +
                "\n" +
                "///////////////////////////////////////\n" +
                "// Tuples (C++11 and above)\n" +
                "///////////////////////////////////////\n" +
                "\n" +
                "#include<tuple>\n" +
                "\n" +
                "// Conceptually, Tuples are similar to old data structures (C-like structs)\n" +
                "// but instead of having named data members,\n" +
                "// its elements are accessed by their order in the tuple.\n" +
                "\n" +
                "// We start with constructing a tuple.\n" +
                "// Packing values into tuple\n" +
                "auto first = make_tuple(10, 'A');\n" +
                "const int maxN = 1e9;\n" +
                "const int maxL = 15;\n" +
                "auto second = make_tuple(maxN, maxL);\n" +
                "\n" +
                "// Printing elements of 'first' tuple\n" +
                "cout << get<0>(first) << \" \" << get<1>(first) << '\\n'; //prints : 10 A\n" +
                "\n" +
                "// Printing elements of 'second' tuple\n" +
                "cout << get<0>(second) << \" \" << get<1>(second) << '\\n'; // prints: 1000000000 15\n" +
                "\n" +
                "// Unpacking tuple into variables\n" +
                "\n" +
                "int first_int;\n" +
                "char first_char;\n" +
                "tie(first_int, first_char) = first;\n" +
                "cout << first_int << \" \" << first_char << '\\n';  // prints : 10 A\n" +
                "\n" +
                "// We can also create tuple like this.\n" +
                "\n" +
                "tuple<int, char, double> third(11, 'A', 3.14141);\n" +
                "// tuple_size returns number of elements in a tuple (as a constexpr)\n" +
                "\n" +
                "cout << tuple_size<decltype(third)>::value << '\\n'; // prints: 3\n" +
                "\n" +
                "// tuple_cat concatenates the elements of all the tuples in the same order.\n" +
                "\n" +
                "auto concatenated_tuple = tuple_cat(first, second, third);\n" +
                "// concatenated_tuple becomes = (10, 'A', 1e9, 15, 11, 'A', 3.14141)\n" +
                "\n" +
                "cout << get<0>(concatenated_tuple) << '\\n'; // prints: 10\n" +
                "cout << get<3>(concatenated_tuple) << '\\n'; // prints: 15\n" +
                "cout << get<5>(concatenated_tuple) << '\\n'; // prints: 'A'\n" +
                "\n" +
                "\n" +
                "///////////////////////////////////\n" +
                "// Logical and Bitwise operators\n" +
                "//////////////////////////////////\n" +
                "\n" +
                "// Most of the operators in C++ are same as in other languages\n" +
                "\n" +
                "// Logical operators\n" +
                "\n" +
                "// C++ uses Short-circuit evaluation for boolean expressions, i.e, the second argument is executed or\n" +
                "// evaluated only if the first argument does not suffice to determine the value of the expression\n" +
                "\n" +
                "true && false // Performs **logical and** to yield false\n" +
                "true || false // Performs **logical or** to yield true\n" +
                "! true        // Performs **logical not** to yield false\n" +
                "\n" +
                "// Instead of using symbols equivalent keywords can be used\n" +
                "true and false // Performs **logical and** to yield false\n" +
                "true or false  // Performs **logical or** to yield true\n" +
                "not true       // Performs **logical not** to yield false\n" +
                "\n" +
                "// Bitwise operators\n" +
                "\n" +
                "// **<<** Left Shift Operator\n" +
                "// << shifts bits to the left\n" +
                "4 << 1 // Shifts bits of 4 to left by 1 to give 8\n" +
                "// x << n can be thought as x * 2^n\n" +
                "\n" +
                "\n" +
                "// **>>** Right Shift Operator\n" +
                "// >> shifts bits to the right\n" +
                "4 >> 1 // Shifts bits of 4 to right by 1 to give 2\n" +
                "// x >> n can be thought as x / 2^n\n" +
                "\n" +
                "~4    // Performs a bitwise not\n" +
                "4 | 3 // Performs bitwise or\n" +
                "4 & 3 // Performs bitwise and\n" +
                "4 ^ 3 // Performs bitwise xor\n" +
                "\n" +
                "// Equivalent keywords are\n" +
                "compl 4    // Performs a bitwise not\n" +
                "4 bitor 3  // Performs bitwise or\n" +
                "4 bitand 3 // Performs bitwise and\n" +
                "4 xor 3    // Performs bitwise xor";
        database = FirebaseDatabase.getInstance("https://tynkr-3915c-default-rtdb.asia-southeast1.firebasedatabase.app/");
        binding.codeView.setOnHighlightListener(this)
                .setOnHighlightListener(this)
                .setTheme(Theme.MONOKAI_SUBLIME)
                .setCode(code)
                .setLanguage(Language.CPP)
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
