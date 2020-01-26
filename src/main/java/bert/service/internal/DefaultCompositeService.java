package bert.service.internal;

import bert.service.CompositeService;
import bert.service.EvenService;
import bert.service.PositiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultCompositeService implements CompositeService {

    @Autowired
    private EvenService evenService;

    @Autowired
    private PositiveService positiveService;

    @Override
    public String analyze(int number) {
        return (evenService.isEven(number) ? "even" : "not even") + ";" + (positiveService.isPositive(number) ? "positive" : "not positive");
    }
}
