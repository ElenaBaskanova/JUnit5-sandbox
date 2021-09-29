package name.baskanov;


public class MyMath 
{
    public static int MySqr(int a)
    {
        return a*a;
    }
    public static int myMod (int a,int b)
    {
        return (a - b*(int)(a/b));
    }
    public static int throwEx (int a) throws NullPointerException
    {
        if (a == 0)
            throw new NullPointerException();
        else
            return a;
    }
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
    
}
