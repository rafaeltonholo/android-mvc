Simple Android MVC
===================
#### NOTE: NOT WORKING WITH ANDROID STUDIO'S INSTANT RUN ON. I'll fix it soon.

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

Declaring a View:

```java
public class MyActivityView extends AbstractActivityView<ModelType> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        // Handle messages from the Controller.
        final Message message = (Message) arg;
        switch (message.getAction()) {
            case "SomeAction":
                model().setName("Teste");
                break;
        }
    }
}
```
See that the Activity (View) implementation is much like the normal. Just need to extends ```AbstractActivityView``` and pass the same type of the model defined on ```Controller```.

## Navigates from Controller to other
Here, we navigates throught Controllers instead Activities. To that, on the ```Controller``` we call the ```getRouter()``` to do that. The ```Router``` can be of two types: ```MemoryRouter``` and ```SerializedRouter``` (not implemented yet). The ```MemoryRouter``` do the navigation from Controllers passing all parameters throught Device's memory, avoiding Serialization usually used in Intent. The ```SerializedRouter``` will use the common mode.
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
      switch (action) {
        case "SomeAction":
            final Parameter param = params[0]; // Parameter received from View.
            final NewObject obj = (NewObject) param.getValue();
            
            // Trace route to OtherController and starts it view.
            getRouter().route(OtherController.class, "The Action I Want", 
                new Parameter("key", "value"),
                new Parameter("model", model()), 
                new Parameter("NewObject", obj));
            break;
      }
    }
}
```
