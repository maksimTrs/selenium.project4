FROM maven:3.8.3-jdk-11

RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN echo 'deb http://dl.google.com/linux/chrome/deb/ stable main' >> /etc/apt/sources.list
RUN apt-get update && apt-get install --no-install-recommends -y google-chrome-stable
# Selectively add stuff we need
COPY pom.xml /usr/src/testng/

# Get a clean build immediately after and 'go-offline' to improve subsequent builds
RUN cd /usr/src/testng && mvn dependency:go-offline
COPY src /usr/src/testng/src
WORKDIR /usr/src/testng/



CMD ["mvn", "-Dtest=SignUpTest,SignUpTest2", "test"]

# docker build -t roronoazorroippo/selenide-spotifyproject1 .
# docker run --name spotifyproject1 --rm roronoazorroippo/selenide-spotifyproject1
# mvn -Dtest=SignUpTest,SignUpTest2 test