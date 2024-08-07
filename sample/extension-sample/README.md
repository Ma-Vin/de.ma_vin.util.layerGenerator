# Example for usage of extending annotations

A small example to show the usage of extending annotations with configuration
at [extensionExampleModel.yaml](./src/main/resources/extensionExampleModel.yaml)

There is a single Entity `ToExtendEntity` defined at yaml file.

* With the annotations `@ExtendingDomain`, `@ExtendingDao` and `@ExtendingDto` the object classes generated by the
  plugin are extended. Each extension got an extra `BigDecimal` Attribute with a different name.
* With the annotations `@ExtendingAccessMapper` and `@ExtendingTransportMapper` the mapper classes generated by the
  plugin are extended. Each extension maps the added `BigDecimal` Attribute.

The extending classes are located
at [main source location and package com.github.ma_vin.util.layer_generator.sample.extension](./src/main/java/com/github/ma_vin/util/layer_generator/sample/extension)

The `convert` methods of `CommonAccessMapper` and `CommonTransportMapper` (which are created by the plugin) make now
usage of the extended classes since the object factories
at [generated/annotation and sub packages of com.github.ma_vin.util.layer_generator.sample.extension.content](./src/main/generated/annotation/java/com/github/ma_vin/util/layer_generator/sample/extension/content)
are creating the extended ones 