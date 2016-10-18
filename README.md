## Intro
This is an incomplete plugin to make something after `make install`

## Build
Build this project using Maven 3+
```
git clone https://github.com/danielef/hook-install
cd hook-install
mvn install
```

## Usage
Create a Java project:
```
mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.1 -DgroupId=com.mycompany.app -DartifactId=my-app -DinteractiveMode=false
```

Configure the plugin from your `pom.xml`
```
<project>
    ...
    <build>
        ...
        <plugins>
            ...
            <plugin>
                <groupId>mx.interware.maven.plugin</groupId>
                <artifactId>hook-install</artifactId>
                <version>1.0-SNAPSHOT</version>
                <executions>
                    <execution>
                        <id>exec0</id>
                        <configuration>
                            <myparam>Hello World!</myparam>
                        </configuration>
                        <goals>
                            <goal>hook</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            ...
            </plugins>
            ...
    </build>
    ...
</project>
```

Build your project using maven install
```
maven install
```
