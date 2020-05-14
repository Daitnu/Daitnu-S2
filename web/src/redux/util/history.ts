import { history } from '~/router';

export const historyPush = (url: string) => {
  history.push(url);
};
