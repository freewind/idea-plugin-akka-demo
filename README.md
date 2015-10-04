How to load this project to IDEA
====================================

This project is demo the problems I have when I want to use akka in IDEA plugin development.

Import to IDEA
---------------

1. import as SBT project from IDEA
2. change project settings, select an IDEA SDK as "Module SDK"
3. compile the project, make sure no compilation error
4. from command line: `./sbt convertToPluginProject`
5. close and open the idea project

It will be an IDEA plugin project with dependencies managed by SBT, and we can run it in another IDEA instance.

Problems
---------

### 1. Can't use default akka configuration

If I removed:

- `src/main/resources/application.conf`
- `src/main/scala/freewind/MyAkkaConfig`

and run this plugin, it will report this error when starting:

```
com.intellij.ide.plugins.PluginManager$StartupAbortedException: 
com.intellij.diagnostic.PluginException: No configuration setting found for key 'akka' 
[Plugin: com.yourcompany.unique.plugin.id]
```

### 2. Can't load the configuration from file

Then I copied the `reference.conf` from akka jar, to `src/main/resources/application.conf`, but it still report the same error. Seems akka in IDEA plugin can't find this file automatically.

### 3. ClassNotFoundException: akka.actor.LightArrayRevolverScheduler 

So I have to use `MyAkkaConfig.scala` to hardcode the configuration in scala code, but this time, it reports another error:

```
com.intellij.ide.plugins.PluginManager$StartupAbortedException: 
com.intellij.diagnostic.PluginException: ClassNotFoundException: akka.actor.LightArrayRevolverScheduler
[Plugin: com.yourcompany.unique.plugin.id]
```

The `akka.actor.LightArrayRevolverScheduler` is used in `MyAkkaConfig.scala`, and is included in `akka-actor_2.11:2.3.12:jar`. But why IDEA can't load it?

