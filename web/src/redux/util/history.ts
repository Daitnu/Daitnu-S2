import { history } from '~/router';

export const push = (url: string) => {
  history.push(url);
};
