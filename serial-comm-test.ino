
#define BAUD 9600

void setup() {
  Serial.begin(BAUD);
  Serial.write("Arduino connected to serial.\n");
}

void loop() {
  while(! (Serial.available() > 0)) continue;             // Wait for some input to serial
  String serialInput = Serial.readStringUntil("\n");   // Get the input from serial
  serialInput.trim();
  Serial.print("Hello from Arduino! You wrote: " + serialInput + "\n");  // Echo the input back 
}
