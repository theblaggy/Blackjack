import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Insets;

/**
 * PageViewer is a support class to resize the cards of a JFrame that´s using CardLayout
 * https://stackoverflow.com/questions/23881651/cardlayout-with-different-sizes#23881790
 * 
 * @author https://stackoverflow.com/users/992484/madprogrammer
 */
public class PageViewer extends CardLayout
{
    @Override
    public Dimension preferredLayoutSize(Container parent)
    {
        Component current = findCurrentComponent(parent);
        if (current != null)
        {
            Insets insets = parent.getInsets();
            Dimension pref = current.getPreferredSize();
            pref.width += insets.left + insets.right;
            pref.height += insets.top + insets.bottom;
            return pref;
        }
        return super.preferredLayoutSize(parent);
    }
    
    public Component findCurrentComponent(Container parent)
    {
        for (Component comp : parent.getComponents())
        {
            if (comp.isVisible())
            {
                return comp;
            }
        }
        return null;
    }
}
