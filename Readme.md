[![build](https://github.com/barakb/opan-api/actions/workflows/build.yml/badge.svg)](https://github.com/barakb/opan-api/actions/workflows/build.yml)
[![Renovate enabled](https://img.shields.io/badge/renovate-enabled-brightgreen.svg)](https://renovatebot.com/)

#### A very simple text based chat on top of OpenAI

you will have to add your openai key to `application.properties` file
`(openai.api.key=mykey)` or set the `OPENAI_API_KEY` environment variable.


* build native container image `mvn -Pnative spring-boot:build-image`
* build native image `mvn -Pnative native:compile`
* to run using maven `mvn spring-boot:run`

#### Example

```bash
>>> who is Horatio Hornblower ?
Horatio Hornblower is a fictional Royal Navy officer who is the protagonist of a series of novels 
by C. S. Forester. He first appeared in the 1937 novel "Mr. Midshipman Hornblower".
The character's fame has endured due to the several films and television programmes that 
have been made based upon the novels. Hornblower is depicted as a capable, intelligent,
and courageous officer, who rises from humble beginnings to become a respected figure in the British navy.

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