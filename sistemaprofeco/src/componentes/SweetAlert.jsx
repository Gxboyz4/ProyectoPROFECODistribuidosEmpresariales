
import { useEffect } from 'react';
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';

export const SweetAlert = ({ message, type, onConfirm, onCancel }) => {
  const MySwal = withReactContent(Swal);

  useEffect(() => {
    if (message) {
      MySwal.fire({
        title: message,
        icon: type || 'info',
        showCancelButton: true,
        confirmButtonText: 'Confirmar',
        cancelButtonText: 'Cancelar',
      }).then((result) => {
        if (result.isConfirmed) {
          onConfirm && onConfirm();
        } else {
          onCancel && onCancel();
        }
      });
    }
  }, [message, type, onConfirm, onCancel]);

  return null;
};

export default SweetAlert;