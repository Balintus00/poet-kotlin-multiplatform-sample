const extraEntry = {
    'webcomponents-loader': '@webcomponents/webcomponentsjs/webcomponents-loader.js',
};

config.entry = config.entry != null ? Object.assign(config.entry, extraEntry) : extraEntry;

if (config.output != null) {
    const kotlinJsFilename = config.output.filename;
    config.output.filename = chunkData =>
        chunkData.chunk.name in extraEntry ? "[name].js" : kotlinJsFilename(chunkData);
}
else
    config.output = { filename: "[name].js" };

config.module.rules.push({
  test: /\.(scss|sass)$/,
  use: [
    /**
     *  fallback to style-loader in development
     *  "style-loader" creates style nodes from JS strings
     */
    "style-loader",   // translates CSS into CommonJS
    "css-loader",   // translates CSS into CommonJS
    "sass-loader"   // compiles Sass to CSS, using Node Sass by default
  ]
});
config.module.rules.push({
  test: /\.css$/,
  use: [
    /**
     *  fallback to style-loader in development
     *  "style-loader" creates style nodes from JS strings
     */
    "style-loader",   // translates CSS into CommonJS
    "css-loader",   // translates CSS into CommonJS
  ]
});
