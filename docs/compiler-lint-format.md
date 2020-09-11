## Compiler flags, linting & formatting

### Scala

The [sbt-scalascout](https://github.com/Scout24/sbt-scalascout) plugin is used to include compiler flags, linting and formatting rules into the project.

#### Linting
There are two linting plugins available in `sbt-scalascout`
 1. [ScoutLintingPlugin](https://github.com/AutoScout24/sbt-scalascout#linting-scala-compiler-flags) includes [default compiler flags](https://docs.scala-lang.org/overviews/compiler-options/index.html) which depend on the target language version. 
 2. [ScoutWartRemoverPlugin](https://github.com/AutoScout24/sbt-scalascout#linting-wartremover) includes the linting tool [WartRemover](http://www.wartremover.org/).

#### Formatting

Formatting is done via [scalafmt](https://scalameta.org/scalafmt/). The plugin includes a [standard configuration](https://github.com/Scout24/sbt-scalascout#formatting) that will apply by default, and can be overridden by specifying a local `.scalafmt.conf` file (not recommended).

It also adds two tasks to the main project

- `format` — format all sources,
- `formatCheck` — check formatting.

The latest IntelliJ versions include native support for `scalafmt` formatting via the `scala` plugin. Be sure to turn on the "format on save" option to automatically format the code as you save your code.

For vscode, the [metals](https://scalameta.org/metals) project includes `scalafmt` support out of the box.

### CloudFormation

#### Linting

CloudFormation templates are validated using AWS CLI and [cfn-lint](https://github.com/aws-cloudformation/cfn-python-lint).
You need to have both installed locally to be able to run the `build.sh` script. `cfn-lint` has also IntelliJ IDEA and Visual Studio Code plugins. Follow the [official docs](https://github.com/aws-cloudformation/cfn-python-lint#install) for the detailed installation instructions.

#### Formatting

We do not auto-format CloudFormation templates.
