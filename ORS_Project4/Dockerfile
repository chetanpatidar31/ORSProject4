FROM tomcat:9

# Copy the WAR file into the Tomcat webapps directory
COPY target/ORS_Project4.war /usr/local/tomcat/webapps/ORS_Project4.war

# Expose port 8080 for the Tomcat server
EXPOSE 8080

# Use the correct script to run Tomcat
CMD ["catalina.sh", "run"]
