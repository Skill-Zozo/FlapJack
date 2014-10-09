package flapjack.builtins;

import flapjack.draw.DefualtPen;
import flapjack.draw.DrawEmptySquare;
import flapjack.draw.DrawSquare;
import flapjack.draw.RandomPenColor;
import flapjack.draw.SetCanvas;
import flapjack.exceptions.*;
import flapjack.machine.Defun;
import flapjack.machine.Else;
import flapjack.machine.Evaluate;
import flapjack.machine.IfThen;
import flapjack.machine.Lambda;
import flapjack.machine.ParamDefun;
import flapjack.machine.Qoute;
import flapjack.machine.ToLet;
import flapjack.machine.While;
import flapjack.types.FJPackage;

public class Builtins{
    public static void registerBuiltins(FJPackage pkg) throws FJException
    {
        // Do any calls necessary to register the builtin functions by insertion into pkg.
    	pkg.register(new PlusFunction(), "+");
    	pkg.register(new MinusFunction(), "-");
    	pkg.register(new MultiplyFunction(), "*");
    	pkg.register(new DivideFunction(), "/");
    	pkg.register(new Equal(), "=");
    	pkg.register(new And(), "and");
    	pkg.register(new BooleanTest(), "boolean?");
    	pkg.register(new IntegerTest(), "integer?");
    	pkg.register(new Get(), "get");
    	pkg.register(new LessThanFunction(), "<");
    	pkg.register(new ModulusFunction(), "mod");
    	pkg.register(new NegFunction(), "neg");
    	pkg.register(new Not(), "not");
    	pkg.register(new Or(), "or");
    	pkg.register(new Set(), "set");
    	pkg.register(new Qoute(), "quote");
    	pkg.register(new SymbolTest(), "symbol?");
    	pkg.register(new IfThen(), "ifthen");
    	pkg.register(new Else(), "else");
    	pkg.register(new Dup(), "dup");
    	pkg.register(new PushEmpty(), "pushempty");
    	pkg.register(new Pop(), "pop");
    	pkg.register(new StackIsEmpty(), "empty?");
    	pkg.register(new StackTest(), "stack?");
    	pkg.register(new Top(), "top");
    	pkg.register(new Rest(), "rest");
    	pkg.register(new Popup(), "popup");
    	pkg.register(new PushDown(), "pushdown");
    	pkg.register(new PushUp(), "pushup");
    	pkg.register(new Reverse(), "reverse");
    	pkg.register(new ReplaceStack(), "replacestack");
    	pkg.register(new StackAll(), "stackall");
    	pkg.register(new PushToAux(), "->aux");
    	pkg.register(new PushFromAux(), "aux->");
    	pkg.register(new PopAux(), "popaux");
    	pkg.register(new StackAux(), "stackaux");
    	pkg.register(new ReplaceAux(), "replaceaux");
    	pkg.register(new PopDown(), "popdown");
    	pkg.register(new Call(), "call");
    	pkg.register(new Eq(), "eq");
    	pkg.register(new PrintLine(), "println");
    	pkg.register(new Print(), "print");
    	pkg.register(new Defun(), "defun");
    	pkg.register(new Evaluate(), "eval");
    	pkg.register(new While(), "while");
    	pkg.register(new Lambda(), "lambda");
    	pkg.register(new DrawSquare(), "drawsquare");
    	pkg.register(new ParamDefun(), "paramdefun");
    	pkg.register(new RandomPenColor(), "randomcolor");
    	pkg.register(new SetCanvas(), "setcanvas");
    	pkg.register(new DefualtPen(), "defualtpen");
    	pkg.register(new DrawEmptySquare(), "drawemptysquare");
    	pkg.register(new ToLet(), "let");
    }
}
