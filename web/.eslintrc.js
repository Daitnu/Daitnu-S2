module.exports = {
  parser: '@typescript-eslint/parser',
  plugins: ['react', '@typescript-eslint'],
  extends: [
    'plugin:react/recommended',
    'plugin:@typescript-eslint/recommended',
    'plugin:prettier/recommended',
  ],
  parserOptions: {
    ecmaVersion: 2018,
  },
  rules: {
    'import/prefer-default-export': 0,
    'no-console': 0,
    'no-trailing-spaces': 0,
    'no-unexpected-multiline': 0,
    'comma-dangle': 0,
    'next-line': 0,
    'object-curly-newline': 0,
    'max-len': ['error', 100],
    'arrow-parens': 0,
    'arrow-body-style': 0,
    'guard-for-in': 0,
    '@typescript-eslint/interface-name-prefix': 0,
    camelcase: 0,
    // '@typescript-eslint/no-inferrable-types': 0,
  },
  settings: {
    react: {
      version: 'detect',
    },
  },
};
