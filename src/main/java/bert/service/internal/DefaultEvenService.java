package bert.service.internal;

import bert.service.EvenService;
import org.springframework.stereotype.Service;

@Service
public class DefaultEvenService implements EvenService {
    @Override
    public boolean isEven(int number) {
        return number % 2 == 0;
    }
}
