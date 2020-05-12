import React from 'react';
import { useSelector } from 'react-redux';
import { RootState } from '~/redux';
import { useHistory } from 'react-router-dom';

export const Home: React.FC = () => {
  const user = useSelector((state: RootState) => state.user);
  const history = useHistory();
  if (user.id === '') {
    history.push('/login');
  }
  return <div>하위 ㅋㅋ</div>;
};
