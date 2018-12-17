package RestApiTesting;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize ;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

public class TestWithHamcrest {

	@Test
	public void test1() {
		assertThat(4,equalTo(4));
		assertThat("abc", is("abc"));
		assertThat("abc", not( equalTo("abcd")  ) );
		assertThat("abc", containsString("a"));
		assertThat("aBC",equalToIgnoringCase("abc"));
		 assertThat(5, greaterThan(4) );
		    assertThat(5, lessThan(6) );
		    assertThat(4, lessThanOrEqualTo(4) );
		    
		    
		    
		    List<Integer> lst = Arrays.asList(2,3,4,5);
		    List<String> words=Arrays.asList("apple","orange","banana");
		    
		    //import static org.hamcrest.collection.IsCollectionWithSize.hasSize ;
		    assertThat(lst, hasSize(4) );
		    
		    assertThat(lst, contains(2,3,4,5) );
		    //assertThat(words,contains("apple"));
		    
		    assertThat(lst, everyItem( greaterThan(1) ) );
	}
}
