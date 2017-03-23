Simple Android MVC
===================
####NOTE: NOTE WORKING WITH ANDROID STUDIO'S INSTANT RUN ON. I'll fix it soon.

This project is a simple implementation of MVC to Android.

Declaring a Controller:

```java
@Controller(MyActivityView.class)
public class MyController extends AbstractController<ModelType> {
    @Override
    protected void onInitialize(Parameter... params) {
      // put here the model initialization.
      model(new ModelType());
    }
    
    @Override
    protected void onMessageReceive(String action, Parameter... params) {
      // Handle messages receiveds from View.
    }
}
```
You don't "really" need to extends AbstractController, but it has some functions that helps to use the framework.
