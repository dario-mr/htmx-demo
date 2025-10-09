import js from '@eslint/js';
import htmlPlugin from '@html-eslint/eslint-plugin';
import htmlParser from '@html-eslint/parser';

export default [
  { ignores: ['node_modules/**', 'target/**', 'build/**'] },

  // JS
  {
    files: ['**/*.js', '**/*.mjs', '**/*.cjs'],
    ...js.configs.recommended,
    languageOptions: { ecmaVersion: 'latest', sourceType: 'module' },
    rules: { 'no-unused-vars': ['warn', { argsIgnorePattern: '^_' }] },
  },

  // HTML (Thymeleaf templates)
  {
    files: ['src/main/resources/templates/**/*.html'],
    languageOptions: { parser: htmlParser },
    plugins: { '@html-eslint': htmlPlugin },
    rules: {
      '@html-eslint/require-closing-tags': 'off',
      '@html-eslint/no-duplicate-attrs': 'error',
      '@html-eslint/no-obsolete-tags': 'off',
    },
  },
];
