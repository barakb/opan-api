[![build](https://github.com/barakb/opan-api/actions/workflows/build.yml/badge.svg)](https://github.com/barakb/opan-api/actions/workflows/build.yml)
[![Renovate enabled](https://img.shields.io/badge/renovate-enabled-brightgreen.svg)](https://renovatebot.com/)

#### A very simple text based chat on top of OpenAI

you will have to add your openai key to `application.properties` file
`(openai.api.key=mykey)` or set the `OPENAI_API_KEY` environment variable.


* build a native container image `mvn -Pnative spring-boot:build-image`
* build a native image `mvn -Pnative native:compile` and run `./target/ai`
* run directly from maven `mvn spring-boot:run`
* build as standalone jar `mvn clean package` and run `java -jar  ./target/ai-0.0.1-SNAPSHOT.jar`


#### Example

```bash
➜  ai git:(master) ✗ ./target/ai
2023-01-28T12:17:53.191+02:00  INFO 90939 --- [           main] org.async.ai.AiApplicationKt             : Starting AOT-processed AiApplicationKt using Java 17.0.5 with PID 90939 (/Users/barak/dev/ai/target/ai started by barak in /Users/barak/dev/ai)
2023-01-28T12:17:53.192+02:00  INFO 90939 --- [           main] org.async.ai.AiApplicationKt             : No active profile set, falling back to 1 default profile: "default"
2023-01-28T12:17:53.211+02:00  INFO 90939 --- [           main] org.async.ai.AiApplicationKt             : Started AiApplicationKt in 0.032 seconds (process running for 0.054)
>>> who is Horatio Hornblower ?
Horatio Hornblower is a fictional Royal Navy officer who is the protagonist of a series of novels 
by C. S. Forester. He first appeared in the 1937 novel "Mr. Midshipman Hornblower".
The character's fame has endured due to the several films and television programmes that 
have been made based upon the novels. Hornblower is depicted as a capable, intelligent,
and courageous officer, who rises from humble beginnings to become a respected figure 
in the British navy.

>>> What are the books that Horation Hornblower character appears in ?
1. The Happy Return (1937)
2. A Ship of the Line (1938)
3. Flying Colours (1938)
4. The Commodore (1945)
5. Lord Hornblower (1946)
6. Mr. Midshipman Hornblower (1950)
7. Lieutenant Hornblower (1952)
8. Hornblower and the Hotspur (1953)
9. Hornblower During the Crisis (1955)
10. Hornblower and the Atropos (1956)
11. Beat to Quarters (1957)
12. Ship of the Line (1958)
13. The Last Encounter (1960)
14. Hornblower in the West Indies (1962)
```