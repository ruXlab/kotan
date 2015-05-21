kotan library
==========================

Easy koding for android using kotlin language

Currently author uses it only in his own projects and it's definitely not even alpha stage but any contributions, ideas
 and requests are welcome.

Find view
----------------------------
Can be done by using delegate: ```LazyView```

```kotlin
public open class BaseActivity : Activity() {
    private val myName by LazyView<TextView>(R.id.mainMyName)
    private val bermudaButton by LazyView<Button?>(R.id.mainBermudaButton)
}
```


If view might exists(i.e. optional)_ in layout you have to make it nullable like **TextView?**, **ListView?**, etc.
This delegate will work for following type of UI components:

* **Activity**. It must be accessed after ```setContentView()```
* **Fragment**. I would recommend to return inflated layout in ```onCreateView()``` and start working with layout in following
lifecycle methods such as ```onActivityCreated()```
* **View**. It's possible to access to UI components anywhere in your code after the inflation process finished(if any).

View events binding
---------------------------------

Main concept of UI event handling is registering callbacks via functions started with **on**, such as ```onClick``` or ```onLongClick```

```kotlin
public open class BaseActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main)

        bermudaButton?.onClick {
            myName.setText("Place your name here")
        }
    }
}
```


ListView
-----------------

List item click handler:

```kotlin
    list.onItemClick<Fruit> { fruit ->
        // process choosed fruit
    }
```



View manipulation
------------------------------

// TODO





SharedProperties / Settings
------------------------------

Working with SharedPreferences is awful I hope you know it. Lets use power of kotlin to make work with settings as simple
as we work with variables


* Let's declare our persistent preferences storage. Just subclass ```Preferences``` providing
  any ```Context``` reference and name of preferences
    ```kotlin
    class Configuration(context: Context): Preferences(context, "cfg") {
        var sheepCounter by super.IntValue(0)
        var myName by super.StringValue("moo")
    }
    ```

  At the moment due bug in [kotlin (KT-6727)](https://youtrack.jetbrains.com/issue/KT-6727#comment=27-935290) you have to insert ```super.``` before name of delegate(which declared in ```Preferences```)

  Currently available: ```StringValue```, ```IntValue```, ```LongValue```, ```FloatValue```

* Then, create instance of this preferences and use it just generic class:
    ```kotlin
    public open class BaseActivity : Activity() {
        val cfg = Configuration(this)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            setContentView(R.layout.main)

            bermudaButton?.onClick {
                cfg.sheepCounter++
                cfg.myName = "${cfg.myName} moo"
                myName.setText(cfg.myName)
            }
        }
    }
    ```


Broadcast messages handling
--------------

Android have very powerful broadcast messaging mechanism but down the road it's always unhandy.

Kotan will help you to use in very easy way

* Describe entities you going to send, as well your broadcast events. Remember your entities **must** implement ```Serializable```
    ```kotlin
    object NEW_MESSAGE: Broadcast<ChatMessage>()
    object DATABASE_UPDATED: Broadcast<DatabaseDetails>()
    object COFFEE_READY: Broadcast<Coffee>()
    ```

* **Send it**.
    ```kotlin
    COFFEE_READY.sendBroadcast(context, Coffee("latte"))
    ```

* **Receive it**. Don't forget to register your receiver
    ```kotlin
    class CoffeeActivity: Activity() {
        val coffeeReceiver = COFFEE_READY.broadcastReceiver(this) { coffee ->
            toastLong("Your ${coffee.name} is ready to drink, sir")
        }

        override fun onStart() {
            super.onStart()
            coffeeReceiver.register(this)
        }

        override fun onStop() {
            super.onStop()
            coffeeReceiver.unregister(this)
        }
    }
    ```


Installation
----------------

* At the moment you can include **kotan** library into your project as submodule in your project
    
        git submodule add https://github.com/ruXlab/kotan kotan


* Also you have to add dependency in ```gradle.build```
  ```gradle
    android {
        dependencies {
            . . .
            compile project(":kotan")
        }
    }
  ```
* And into ```settings.build```
  ```gradle
  include ':kotan'
  ```
    
* Final step - import into your namespace
    
        import vc.rux.kotan.*
