//This class will deal with the application's look and feel:
// 1) set it to a dark theme
// 2) and set it to a light theme

package Theme_Class;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;

public class LookAndFeel 
{
    //set dark theme
    public static void LookAndFeel_Dark()
    {
        try 
        {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } 
        catch( Exception ex ) 
        {
            System.err.println( "Failed to initialize LaF" );
        }
    }
    
    //set light theme
    public static void LookAndFeel_Light()
    {
        try 
        {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } 
        catch( Exception ex ) 
        {
            System.err.println( "Failed to initialize LaF" );
        }
    }
}
