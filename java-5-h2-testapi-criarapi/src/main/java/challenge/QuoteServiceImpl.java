package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository repository;

    @Override
    public Quote getQuote() {
        List<Quote> listQuotes = repository.findAll();
        Collections.shuffle(listQuotes);

        if (listQuotes.isEmpty()) {
            return null;
        } else {
            return listQuotes.get(0);
        }
    }

    @Override
    public Quote getQuoteByActor(String actor) {
        List<Quote> listQuotes = repository.findByActor(actor);
        Collections.shuffle(listQuotes);
        if (listQuotes.isEmpty()) {
            return null;
        } else {
            return listQuotes.get(0);
        }
    }

}
