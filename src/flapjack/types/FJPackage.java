package flapjack.types;

//import flapjack.exceptions.FJException;
import flapjack.exceptions.IllegalRedefinitionException;
import flapjack.exceptions.SymbolClashException;

public class FJPackage {
    /* TODO: Instance variables to store pairs of strings and FJSymbols */
   // private LinkedList<String> programStrings = new LinkedList<String>();
    private BST<String, FJSymbol> programSymbols;
   
    public FJPackage() {
    	 programSymbols = new BST<String, FJSymbol>();
    }
    
    public FJSymbol intern(String name)
    {
        /* If a symbol with name name already exists in the package,
           simply return that symbol. Otherwise, create a new symbol
           with that name, enter it into the package, and return it */
    	FJSymbol isThere = this.programSymbols.get(name);
    	if (isThere != null) {	
    		return isThere;/*
        	for(int i = 0; i < this.programSymbols.getSize() + 1; i++) {
        		if(this.programSymbols.get(name).equalsIgnoreCase(name)){
        			isThere = this.programSymbols.getItem(i);
        			return isThere;
        		}
        	}*/
        } else {
	    	FJSymbol added = new FJSymbol(name);
	    	/*this.programStrings.push(name);
	    	this.programSymbols.push(added);*/
	    	this.programSymbols.put(name, added);
	    	return added;
        }
    }
    
    public void intern(FJSymbol sym) throws SymbolClashException
    {
        /* If a symbol with the same name as the name of the symbol sym 
         already exists in the package, throw a SymbolClashException.
         Otherwise, enter the symbol sym into the package.
         The difference between this and intern(String name) is that you
         do not create any new symbols here.
        */
    	if(programSymbols.get(sym.getSymbolName()) != null) {
    		throw new SymbolClashException("Syntax error. Symbol name already exists. System will exit.");
    	} else {
    		programSymbols.put(sym.getSymbolName(), sym);
    	}
    }
    
    public FJSymbol register(FlapjackObject obj, String name) throws IllegalRedefinitionException
    {
    	
    	FJSymbol returned = this.intern(name);
    	returned.setValue(obj);
    	return returned;
    	/*urntry {
    		returned = (FJSymbol)(obj);
        } catch(Exception e) {
        	throw new IllegalRedefinitionException("Couldn't register symbol. System will exit");
        }
    	/* Intern a symbol with name name, set the value of the 
           interned symbol to obj, and return the interned symbol. */
    }
    
    public BST getList() {
    	return this.programSymbols;
    }
    
    public void print() {
    	this.programSymbols.String();
    }
    
	public static boolean legalSymbolRedef(String sym) {
		String s  = sym;
		return !(s.equals("while") || s.equals("lambda") || s.equals("defun") || s.equals("ifthen") || s.equals("eval") || s.equals("quote"));
	}
}