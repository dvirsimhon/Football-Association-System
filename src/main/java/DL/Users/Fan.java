package DL.Users;

import DL.Team.Page.Page;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Description:     Represents a Fan in the System. A Fan is the first User object that a registered User gets.
 * ID:              7
 **/
@NamedQueries( value = {
        @NamedQuery(name = "AllFans", query = "SELECT f From Fan f")
})
@Entity
@DiscriminatorValue(value = "Fan")
public class Fan extends User implements Serializable
{

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Page> follow;

    public Fan (String userName, String email, String hashedPassword)
    {
        super(userName, email, hashedPassword);
        follow = new HashSet<>();
    }


    public Fan()
    {
        this("", "", "");
    }

    /**
     *
     * @param page
     * @return true if the fan follows the page
     */
    public boolean followPage(Page page)
    {
        if(page == null)
        {
            return false;
        }

        if(follow.add(page))
        {
            if(page.addFollower(this))
            {
                return true;
            }
            follow.remove(page);
        }

        return false;
    }

    public boolean unfollowPage(Page page)
    {
        if(page == null)
        {
            return false;
        }

        if(follow.remove(page))
        {
            if(page.removeFollower(this))
            {
                return true;
            }
            follow.add(page);
        }
        return false;
    }

    public boolean unfollowAllPages()
    {
        Iterator<Page> it = follow.iterator();
        while(it.hasNext())
        {
            Page page = it.next();
            page.removeFollower(this);
        }

        this.follow = new HashSet<>();

        return true;
    }

    public Set<Page> getFollowing()
    {
        return this.follow;
    }

}
