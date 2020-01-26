package bert.service.internal;

import bert.service.PositiveService;

public class DefaultPositiveService implements PositiveService {
    @Override
    public boolean isPositive(int number) {
        return number > 0;
    }
}
