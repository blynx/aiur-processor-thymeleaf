let { execSync } = require("child_process");
let path = require("path");

function quote(string) {
  return '"' + string.replace(/\\/gm, "\\\\").replace(/"/gm, '\\"') + '"';
}

/**
 * TODO: consider proper / more thorough escaping for terminal interaction!
 */

module.exports = (templateFilePath, data, config) => {
  let { prefix = process.env.PWD } = config;
  let jarfile = path.resolve(__dirname, "bundle/aiur-processor-thymeleaf-all.jar");

  let command = ["java -jar " + jarfile];
  command.push("--prefix " + quote(prefix));
  command.push("--data " + quote(JSON.stringify(data)));
  command.push(quote(templateFilePath));

  // strip new lines, returns
  let commandString = command.join(" ").replace(/(\r\n|\n|\r)/gm, "");

  return new Promise((resolve, reject) => {
    try {
      let output = execSync(commandString);
      resolve(output);
    } catch (error) {
      reject(error);
    }
  });
};
