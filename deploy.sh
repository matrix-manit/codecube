# This script should be run from same directory as your project base directory.
# You may need to adjust the directories if needed.

# Stop Tomcat
sudo service tomcat7 stop

# Remove the old deployed WAR file.
sudo rm -rf /var/lib/tomcat7/webapps/codecube.war

# Remove the extracted WAR.
sudo rm -rf /var/lib/tomcat7/webapps/codecube

# Copy the new WAR to the folder from which tomcat picks.
sudo cp ./target/codecube.war /var/lib/tomcat7/webapps/

# Start Tomcat
sudo service tomcat7 start 

# Displays logs. Comment if you do need to display logs on the same screen.
tail -f /var/log/tomcat7/catalina.out
