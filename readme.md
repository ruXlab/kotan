kotan library
----------------------

Will try make easier to kode for android using kotlin language

Currently author uses it only in his own projects and it's definitely not even alpha stage but any contributions, ideas
 and requests are welcome.

Find view
======================
Can be done by using delegate: ```LazyView```

    public open class BaseActivity : Activity() {
        private val myName by LazyView<TextView>(R.id.mainMyName)
        private val bermudaButton by LazyView<Button?>(R.id.mainBermudaButton)
    }


If view _can be_ in layout you have to make it nullable

View events binding
======================

Main concept of UI event handling is registering callbacks via functions started with **on**, such as ```onClick``` or ```onLongClick```

    public open class BaseActivity : Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            setContentView(R.layout.main)

            bermudaButton?.onClick {
                myName.setText("Place your name here")
            }
        }
    }





View manipulation
===================

// TODO



SharedProperties / Settings
============================

Working with SharedPreferences is awful I hope you know it. Lets use power of kotlin to make work with settings as simple
as we work with variables


* Let's declare our persistent preferences storage. Just subclass ```Preferences``` providing
  any ```Context``` reference and name of preferences

        class Configuration(context: Context): Preferences(context, "cfg") {
            var sheepCounter by super.IntValue(0)
            var myName by super.StringValue("moo")
        }

  At the moment due bug in [kotlin (KT-6727)](https://youtrack.jetbrains.com/issue/KT-6727#comment=27-935290) you have to insert ```super.``` before name of delegate(which declared in ```Preferences```)

  Currently available: ```StringValue```, ```IntValue```, ```LongValue```, ```FloatValue```

* Then, create instance of this preferences and use it just generic class:

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
