package com.example;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import murex.test.rita.books.Book;
import murex.test.rita.books.Library;
import murex.test.rita.books.ListMaker;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RCPopescu on 8/25/2016.
 */
public class GetTitleBookStepDefinition {

    Library library;
    private List<Book> booksRepo = new ArrayList<Book>();
    ListMaker lm = new ListMaker();
    String bookTitle;

    @Given("^I receive a directory of books$")
    public void i_receive_a_directory_of_books() throws Throwable {
        booksRepo = lm.createBookList();
        library = new Library(booksRepo);
    }

    @When("^I give the ISBN \"([^\"]*)\"$")
    public void i_give_the_ISBN(String iSBN) throws Throwable {
        bookTitle = library.getTitleBookByISBN(iSBN);
    }

    @Then("^I receive the title \"([^\"]*)\"$")
    public void i_receive_the_title(String title) throws Throwable {
        Assert.assertEquals(title, bookTitle);
    }
}
