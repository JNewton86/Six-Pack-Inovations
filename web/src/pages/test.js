const main = async () => {
    console.log('I created a new page!');
};

window.addEventListener('DOMContentLoaded', main);

entry: {
    createPlaylist: path.resolve(__dirname, 'src', 'pages', 'createPlaylist.js'),
    viewPlaylist: path.resolve(__dirname, 'src', 'pages', 'viewPlaylist.js'),
    test: path.resolve(__dirname, 'src', 'pages', 'test.js'),
    },