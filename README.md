![Maven Workflow Action](https://github.com/Ma-Vin/layer-generator/actions/workflows/release.yml/badge.svg)

# Util Layer Generator

A maven plugin generator and annotations to create domain model, data transport or data access objects and their
mappers.

## Sonarcloud analysis

* [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?branch=release%2Fv2.0.0&project=Ma-Vin_de.ma_vin.util.layerGenerator&metric=alert_status)](https://sonarcloud.io/summary/overall?id=Ma-Vin_de.ma_vin.util.layerGenerator&branch=release%2Fv2.0.0)
* [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?branch=release%2Fv2.0.0&project=Ma-Vin_de.ma_vin.util.layerGenerator&metric=reliability_rating)](https://sonarcloud.io/summary/overall?id=Ma-Vin_de.ma_vin.util.layerGenerator&branch=release%2Fv2.0.0)  [![Bugs](https://sonarcloud.io/api/project_badges/measure?branch=release%2Fv2.0.0&project=Ma-Vin_de.ma_vin.util.layerGenerator&metric=bugs)](https://sonarcloud.io/summary/overall?id=Ma-Vin_de.ma_vin.util.layerGenerator&branch=release%2Fv2.0.0)
* [![Security Rating](https://sonarcloud.io/api/project_badges/measure?branch=release%2Fv2.0.0&project=Ma-Vin_de.ma_vin.util.layerGenerator&metric=security_rating)](https://sonarcloud.io/summary/overall?id=Ma-Vin_de.ma_vin.util.layerGenerator&branch=release%2Fv2.0.0)  [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?branch=release%2Fv2.0.0&project=Ma-Vin_de.ma_vin.util.layerGenerator&metric=vulnerabilities)](https://sonarcloud.io/summary/overall?id=Ma-Vin_de.ma_vin.util.layerGenerator&branch=release%2Fv2.0.0)
* [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?branch=release%2Fv2.0.0&project=Ma-Vin_de.ma_vin.util.layerGenerator&metric=sqale_rating)](https://sonarcloud.io/summary/overall?id=Ma-Vin_de.ma_vin.util.layerGenerator&branch=release%2Fv2.0.0)  [![Technical Debt](https://sonarcloud.io/api/project_badges/measure?branch=release%2Fv2.0.0&project=Ma-Vin_de.ma_vin.util.layerGenerator&metric=sqale_index)](https://sonarcloud.io/summary/overall?id=Ma-Vin_de.ma_vin.util.layerGenerator&branch=release%2Fv2.0.0)  [![Code Smells](https://sonarcloud.io/api/project_badges/measure?branch=release%2Fv2.0.0&project=Ma-Vin_de.ma_vin.util.layerGenerator&metric=code_smells)](https://sonarcloud.io/summary/overall?id=Ma-Vin_de.ma_vin.util.layerGenerator&branch=release%2Fv2.0.0)
* [![Coverage](https://sonarcloud.io/api/project_badges/measure?branch=release%2Fv2.0.0&project=Ma-Vin_de.ma_vin.util.layerGenerator&metric=coverage)](https://sonarcloud.io/summary/overall?id=Ma-Vin_de.ma_vin.util.layerGenerator&branch=release%2Fv2.0.0)
* [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?branch=release%2Fv2.0.0&project=Ma-Vin_de.ma_vin.util.layerGenerator&metric=duplicated_lines_density)](https://sonarcloud.io/summary/overall?id=Ma-Vin_de.ma_vin.util.layerGenerator&branch=release%2Fv2.0.0)  [![Lines of Code](https://sonarcloud.io/api/project_badges/measure?branch=release%2Fv2.0.0&project=Ma-Vin_de.ma_vin.util.layerGenerator&metric=ncloc)](https://sonarcloud.io/summary/overall?id=Ma-Vin_de.ma_vin.util.layerGenerator&branch=release%2Fv2.0.0)

## Usage

For usage the artifact `com.github.ma_vin.util.layer_generator:extension-annotation` is needed as dependency
and `com.github.ma_vin.util.layer_generator:generator-plugin` should be included as maven plugin. See details at their
readme markdowns at [extension-annotation](extension-annotation) and [generator-plugin](./generator-plugin).

It is possible to use the artifact `com.github.ma_vin.util.layer_generator:generator-annotation` instead of the maven
plugin. See details at its readme markdowns at [generator-annotation](./generator-annotation).

### Annotation for Extension

The implementation of the annotations to extend generated classes by custom code is located
at [extension-annotation](/extension-annotation)

### Maven plugin generator

The implementation of the maven plugin is located at [generator-plugin](/generator-plugin)

### Annotation generator

The implementation of the annotation generator is located at [generator-annotation](/generator-annotation)

### Example

There are few example at [sample](./sample) (The generated code is commited also). 
The usage of following elements is shown:

* accessibility
  * `plugin` at [plugin-sample](./sample/plugin-sample)
  * `annotation` at [annotation-sample](./sample/annotation-sample)
* configuration elements
  * `entity`, `index` at [entity-sample](./sample/entity-sample)
  * `reference` at [reference-sample](./sample/reference-sample)
  * `grouping` at [grouping-sample](./sample/grouping-sample)
  * `field`, `daoInfo` at [field-sample](./sample/field-sample)
  * `version` at [version-sample](./sample/version-sample)

## Release features and changes

### Main changes of version 2.0.0 compared to [v1.4](https://github.com/Ma-Vin/layer-generator/releases/tag/v1.4)
* renaming/moving: 
  * The GitHub project is renamed to `layer-generator`
  * the groupId renamed to `com.github.ma_vin.util.layer_generator`
  * the artifactId changed from `layer-generator-annotation` to `extension-annotation`
  * the artifactId changed from `layer-generator-plugin` to `generator-plugin` and some elements are move into `base-layer-generator`
  * the artifactId changed from `layer-generator-sources` to `sourcecode-generator`
  * the artifactId changed from `layer-generator-sample` to `sample` and sub modules
* accessibility with an annotation in addition to plugin configuration. See also `generator-annotation`
* versioning of data transport objects. Can be used to provide a deprecated and an actual API
* different types of configuration formats: yaml and json are added