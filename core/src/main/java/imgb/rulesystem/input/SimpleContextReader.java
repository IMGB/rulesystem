package imgb.rulesystem.input;

import imgb.rulesystem.context.Context;

/**
 * Created by is on 1/13/15.
 */
public class SimpleContextReader extends ContextReader {

    @Override
    public Context getContext() {
        return new Context();
    }
}
